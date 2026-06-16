package com.arkan.portalartikel.service;

import com.arkan.portalartikel.model.Comment;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    public void simpanKomentar(Comment comment) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            db.collection("comments").add(comment).get();
            System.out.println("Komentar berhasil disimpan!");
        } catch (Exception e) {
            System.err.println("Gagal menyimpan komentar: " + e.getMessage());
        }
    }

    public List<Comment> ambilKomentarBerdasarkanArtikel(String articleId) {
        List<Comment> commentList = new ArrayList<>();
        try {
            Firestore db = FirestoreClient.getFirestore();
            QuerySnapshot querySnapshot = db.collection("comments")
                    .whereEqualTo("articleId", articleId)
                    .get().get();

            querySnapshot.getDocuments().forEach(document -> {
                Comment comment = document.toObject(Comment.class);
                if (comment != null) {
                    comment.setId(document.getId());
                    commentList.add(comment);
                }
            });
        } catch (Exception e) {
            System.err.println("Gagal mengambil komentar: " + e.getMessage());
        }
        return commentList;
    }
}