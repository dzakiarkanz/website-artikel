package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.Article;
import com.arkan.portalartikel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;

    // Nama web terpusat agar mudah diganti jika ingin diubah lagi nanti
    private final String NAMA_WEB = "Tech Pulse"; 

    @GetMapping("/")
    public String home(Model model) {
        // Mengirim nama web dinamis ke indeks
        model.addAttribute("webName", NAMA_WEB);
        
        model.addAttribute("featuredArticles", articleService.ambilSemuaArtikel());
        model.addAttribute("latestArticle", articleService.ambilArtikelTerbaru());
        model.addAttribute("popularCategories", List.of("Tech", "Tutorials", "AI & Dev"));

        return "index";
    }

    // TAMBAHAN: Routing dinamis untuk halaman detail artikel berdasarkan ID
    @GetMapping("/artikel/{id}")
    public String detailArtikel(@PathVariable("id") Long id, Model model) {
        // Mengirim nama web ke halaman detail
        model.addAttribute("webName", NAMA_WEB);

        // Cari artikel ke service berdasarkan ID dari URL
        Article article = articleService.ambilArtikelBerdasarkanId(id);
        
        // Antisipasi jika user iseng mengetik ID yang tidak ada di database
        if (article == null) {
            return "redirect:/"; // Kembalikan ke halaman utama
        }

        // Kirim data objek artikel tunggal ke HTML detail
        model.addAttribute("article", article);
        return "detail";
    }
}