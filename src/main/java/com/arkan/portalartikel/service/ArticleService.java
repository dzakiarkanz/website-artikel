package com.arkan.portalartikel.service;

import org.springframework.stereotype.Service;
import com.arkan.portalartikel.model.Article;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    public List<Article> ambilSemuaArtikel() {
        List<Article> articleList = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            QuerySnapshot querySnapshot = db.collection("articles").get().get();
            
            querySnapshot.getDocuments().forEach(document -> {
                Article article = document.toObject(Article.class);
                if (article != null) {
                    article.setId(document.getId()); 
                    articleList.add(article);
                }
            });
        } catch (Exception e) {
            System.err.println("Gagal mengambil artikel: " + e.getMessage());
        }
        return articleList;
    }

    public void simpanArtikel(Article article) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            if (article.getId() == null || article.getId().isEmpty()) {
                db.collection("articles").add(article).get();
            } else {
                db.collection("articles").document(article.getId()).set(article).get();
            }
            System.out.println("Artikel berhasil diproses di Firebase!");
        } catch (Exception e) {
            System.err.println("Gagal memproses artikel: " + e.getMessage());
        }
    }

    public Article ambilArtikelBerdasarkanId(String id) { 
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentSnapshot document = db.collection("articles").document(id).get().get();
            if (document.exists()) {
                Article article = document.toObject(Article.class);
                if (article != null) {
                    article.setId(document.getId());
                    return article;
                }
            }
        } catch (Exception e) {
            System.err.println("Gagal mengambil artikel: " + e.getMessage());
        }
        return null;
    }

    public void hapusArtikelBerdasarkanId(String id) { 
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection("articles").document(id).delete().get();
        } catch (Exception e) {
            System.err.println("Gagal menghapus artikel: " + e.getMessage());
        }
    }
}