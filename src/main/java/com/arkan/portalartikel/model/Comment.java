package com.arkan.portalartikel.model;

public class Comment {
    private String id; 
    private String content;
    private String createdAt;
    private String articleId; 
    private String username;  

    public Comment() {}

    public Comment(String content, String createdAt, String articleId, String username) {
        this.content = content;
        this.createdAt = createdAt;
        this.articleId = articleId;
        this.username = username;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getArticleId() { return articleId; }
    public void setArticleId(String articleId) { this.articleId = articleId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}