package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.Article;
import com.arkan.portalartikel.model.Comment;
import com.arkan.portalartikel.model.User;
import com.arkan.portalartikel.repository.UserRepository;
import com.arkan.portalartikel.service.ArticleService;
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

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    // Memproses kiriman komentar dari form detail artikel
    @PostMapping("/article/{articleId}/comment")
    public String simpanKomentar(@PathVariable("articleId") Long articleId,
                                 @RequestParam("content") String content,
                                 Principal principal) {
        
        // 1. Ambil data artikel berdasarkan ID dari URL
        Article article = articleService.ambilArtikelBerdasarkanId(articleId);
        
        // 2. Ambil data user yang sedang login dari database menggunakan objek Principal
        String usernameSesi = principal.getName();
        User user = userRepository.findByUsername(usernameSesi)
                .orElseThrow(() -> new RuntimeException("User sesi tidak ditemukan"));

        // 3. Format waktu pembuatan komentar saat ini
        LocalDateTime sekarang = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm");
        String tanggalDiformat = sekarang.format(formatter);

        // 4. Instansiasi objek komentar baru dan set relasinya
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedAt(tanggalDiformat);
        comment.setArticle(article);
        comment.setUser(user);

        // 5. Simpan ke database melalui layer service
        commentService.simpanKomentar(comment);

        // 6. Kembalikan tampilan ke halaman detail artikel yang sama
        return "redirect:/article/" + articleId;
    }
}