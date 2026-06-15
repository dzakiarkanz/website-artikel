package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        // Mengambil data dinamis dari database melalui Service
        model.addAttribute("featuredArticles", articleService.ambilSemuaArtikel());
        model.addAttribute("latestArticle", articleService.ambilArtikelTerbaru());
        
        // Data kategori statis untuk tampilan sidebar
        model.addAttribute("popularCategories", List.of("Tech", "Tutorials", "AI & Dev"));

        return "index";
    }
}