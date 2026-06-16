package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.Comment;
import com.arkan.portalartikel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/article/{articleId}/comment")
    public String simpanKomentar(@PathVariable("articleId") String articleId, 
                                 @RequestParam("content") String content,
                                 Principal principal) {
        
        String usernameSesi = (principal != null) ? principal.getName() : "Anonim";

        LocalDateTime sekarang = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm");
        String tanggalDiformat = sekarang.format(formatter);

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedAt(tanggalDiformat);
        comment.setArticleId(articleId);
        comment.setUsername(usernameSesi);

        commentService.simpanKomentar(comment);

        return "redirect:/article/" + articleId;
    }
}