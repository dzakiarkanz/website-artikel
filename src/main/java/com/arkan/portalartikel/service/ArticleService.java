package com.arkan.portalartikel.service;

import com.arkan.portalartikel.model.Article;
import com.arkan.portalartikel.repository.ArticleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    // Otomatis mengisi database dengan data awal jika masih kosong saat aplikasi pertama kali jalan
    @PostConstruct
    public void initData() {
        if (articleRepository.count() == 0) {
            articleRepository.saveAll(List.of(
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
        }
    }

    // Fungsi mengambil semua data artikel untuk halaman utama
    public List<Article> ambilSemuaArtikel() {
        return articleRepository.findAll();
    }

    // Fungsi mengambil satu artikel terbaru untuk panel samping
    public Article ambilArtikelTerbaru() {
        List<Article> semua = articleRepository.findAll();
        if (semua.isEmpty()) {
            return null;
        }
        // Mengembalikan artikel terakhir yang dimasukkan
        return semua.get(semua.size() - 1);
    }
}