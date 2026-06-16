package com.arkan.portalartikel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String createdAt;

    // Menghubungkan komentar ke artikel spesifik
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    // Menghubungkan komentar ke user yang menulisnya
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructor Kosong
    public Comment() {
    }

    // Constructor Lengkap
    public Comment(String content, String createdAt, Article article, User user) {
        this.content = content;
        this.createdAt = createdAt;
        this.article = article;
        this.user = user;
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}