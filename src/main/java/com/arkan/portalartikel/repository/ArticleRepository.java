package com.arkan.portalartikel.repository;

import com.arkan.portalartikel.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // JpaRepository otomatis menyediakan fungsi bawaan:
    // save(), findAll(), findById(), deleteById(), dll.
}