package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.Article;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredArticles", List.of(
                new Article("JAVA", "Panduan Lengkap Spring Boot Bootstrap",
                        "Belajar memahami struktur aplikasi modern dengan Spring Boot, Bootstrap, dan template engine Thymeleaf.",
                        "Dzaki Arkan", "10 Juni 2026"),
                new Article("WEB DEV", "Membuat Website Artikel Sederhana",
                        "Implementasi CRUD sederhana untuk platform blog dengan Spring Boot Web dan Thymeleaf.",
                        "Admin Tech", "10 Juni 2026"),
                new Article("AI", "Pengantar Auto-Configuration di Spring",
                        "Kenali cara kerja auto-configuration agar proyek lebih cepat dikembangkan dan mudah dipelihara.",
                        "Dzaki Arkan", "10 Juni 2026"),
                new Article("AI", "Tips & Trik Thymeleaf Template Engine",
                        "Optimalkan rendering halaman dinamis dengan pendekatan template yang rapi dan konsisten.",
                        "Admin Tech", "10 Juni 2026")
        ));

        model.addAttribute("popularCategories", List.of("Kategori", "Tech", "Tutorials", "AI & Dev"));
        model.addAttribute("latestArticle",
                new Article("WEB DEV", "Membuat Website Artikel Sederhana",
                        "", "", "10 Juni 2026"));

        return "index";
    }
}