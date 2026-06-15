package com.arkan.portalartikel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column; // Tambahan untuk tipe data besar

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String title;
    private String excerpt;
    private String author;
    private String publishedAt;

    // TAMBAHAN: Field untuk isi berita utuh. 
    // @Column(columnDefinition = "TEXT") memastikan database bisa menampung teks yang sangat panjang.
    @Column(columnDefinition = "TEXT")
    private String content; 

    // Constructor Kosong (Wajib untuk JPA)
    public Article() {
    }

    // Constructor Lengkap Baru (Sudut pandang parameter memasukkan 'content')
    public Article(String category, String title, String excerpt, String content, String author, String publishedAt) {
        this.category = category;
        this.title = title;
        this.excerpt = excerpt;
        this.content = content;
        this.author = author;
        this.publishedAt = publishedAt;
    }

    // Getter dan Setter
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getCategory() { 
        return category; 
    }
    
    public void setCategory(String category) { 
        this.category = category; 
    }

    public String getTitle() { 
        return title; 
    }
    
    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getExcerpt() { 
        return excerpt; 
    }
    
    public void setExcerpt(String excerpt) { 
        this.excerpt = excerpt; 
    }

    public String getContent() { 
        return content; 
    }
    
    public void setContent(String content) { 
        this.content = content; 
    }

    public String getAuthor() { 
        return author; 
    }
    
    public void setAuthor(String author) { 
        this.author = author; 
    }

    public String getPublishedAt() { 
        return publishedAt; 
    }
    
    public void setPublishedAt(String publishedAt) { 
        this.publishedAt = publishedAt; 
    }
}