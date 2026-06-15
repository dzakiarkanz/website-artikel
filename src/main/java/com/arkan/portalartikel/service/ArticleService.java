package com.arkan.portalartikel.service;

import com.arkan.portalartikel.model.Article;
import com.arkan.portalartikel.repository.ArticleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    // Pastikan di sini hanya ada 3 objek Article, masing-masing memiliki 6 argumen String (termasuk isi berita)
    @PostConstruct
    public void initData() {
        if (articleRepository.count() == 0) {
            articleRepository.saveAll(List.of(
                new Article("JAVA", 
                        "Panduan Lengkap Spring Boot Bootstrap",
                        "Belajar memahami struktur aplikasi modern dengan Spring Boot, Bootstrap, dan template engine Thymeleaf.",
                        "Spring Boot merupakan salah satu framework Java yang paling populer untuk mengembangkan aplikasi berbasis web dan RESTful API. Dalam panduan ini, kita membedah bagaimana integrasi Bootstrap sebagai front-end dan Thymeleaf sebagai template engine dapat mempercepat proses development. Arsitektur berlapis (Layered Architecture) yang memisahkan Model, Repository, Service, dan Controller menjamin kode Anda tetap rapi, mudah di-scale, dan maintainable dalam jangka panjang.",
                        "Dzaki Arkan", "15 Juni 2026"),
                
                new Article("WEB DEV", 
                        "Membuat Website Artikel Sederhana",
                        "Implementasi CRUD sederhana untuk platform blog dengan Spring Boot Web dan Thymeleaf.",
                        "Membuat website artikel atau blog merupakan proyek fundamental yang sangat bagus untuk memahami konsep dasar web development. Dengan memanfaatkan Spring Boot Web, kita bisa mengatur routing URL dengan mudah. Melalui pembahasan ini, Anda akan diajak mempraktikkan bagamana data dikirim dari Controller menuju halaman HTML menggunakan ekspresi Thymeleaf secara dinamis tanpa perlu menulis JavaScript yang rumit.",
                        "Admin Tech", "15 Juni 2026"),
                
                new Article("AI", 
                        "Pengantar Auto-Configuration di Spring",
                        "Kenali cara kerja auto-configuration agar proyek lebih cepat dikembangkan.",
                        "Salah satu keajaiban utama dari Spring Boot adalah fitur Auto-Configuration. Fitur ini secara cerdas mendeteksi library apa saja yang ada di dalam berkas pom.xml Anda dan langsung mengonfigurasinya secara otomatis. Sebagai contoh, ketika kita memasukkan dependensi H2 Database, Spring Boot langsung menyiapkan database in-memory siap pakai di latar belakang tanpa mengharuskan kita menulis baris kode konfigurasi bean database sama sekali.",
                        "Dzaki Arkan", "15 Juni 2026")
            ));
        }
    }

    public List<Article> ambilSemuaArtikel() {
        return articleRepository.findAll();
    }

    public Article ambilArtikelTerbaru() {
        List<Article> semua = articleRepository.findAll();
        if (semua.isEmpty()) {
            return null;
        }
        return semua.get(semua.size() - 1);
    }

    // Method ini wajib ada agar HomeController tidak error line 41
    public Article ambilArtikelBerdasarkanId(Long id) {
        Optional<Article> artikel = articleRepository.findById(id);
        return artikel.orElse(null);
    }
}