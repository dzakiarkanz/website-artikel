package com.arkan.portalartikel.service;

import com.arkan.portalartikel.model.Comment;
import com.arkan.portalartikel.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void simpanKomentar(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> ambilKomentarBerdasarkanArtikel(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}