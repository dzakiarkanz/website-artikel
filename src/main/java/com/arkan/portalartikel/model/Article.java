package com.arkan.portalartikel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    // Constructor Kosong (Wajib untuk JPA/Hibernate)
    public Article() {
    }

    // Constructor Lengkap (Untuk mempermudah instansiasi data)
    public Article(String category, String title, String excerpt, String author, String publishedAt) {
        this.category = category;
        this.title = title;
        this.excerpt = excerpt;
        this.author = author;
        this.publishedAt = publishedAt;
    }

    // Getter dan Setter (Wajib untuk Thymeleaf dan Operasi CRUD)
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