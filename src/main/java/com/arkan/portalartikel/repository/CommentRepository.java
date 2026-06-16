package com.arkan.portalartikel.repository;

import com.arkan.portalartikel.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Fungsi khusus untuk mengambil semua komentar yang ada di satu artikel tertentu
    List<Comment> findByArticleId(Long articleId);
}