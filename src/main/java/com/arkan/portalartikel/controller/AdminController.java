package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.Article;
import com.arkan.portalartikel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin") // Semua URL di controller ini akan diawali dengan /admin
public class AdminController {

    @Autowired
    private ArticleService articleService;

    private final String NAMA_WEB = "Tech Pulse";

    // 1. Menampilkan Dashboard Tabel Kelola Artikel
    @GetMapping("/artikel")
    public String kelolaArtikel(Model model) {
        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("articles", articleService.ambilSemuaArtikel());
        return "kelola-artikel"; // Akan mengarah ke templates/kelola-artikel.html
    }

    // 2. Menampilkan Form untuk Tambah Artikel Baru
    @GetMapping("/artikel/tambah")
    public String formTambahArtikel(Model model) {
        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("pageTitle", "Tambah Artikel Baru");
        
        // Mengirim objek Article kosong agar bisa diikat (binding) ke Form HTML
        model.addAttribute("article", new Article()); 
        return "form-artikel"; // Akan mengarah ke templates/form-artikel.html
    }

    // 3. Memproses Penyimpanan Data dari Form (Bisa Tambah Baru atau Simpan Hasil Edit)
    @PostMapping("/artikel/simpan")
    public String simpanArtikel(@ModelAttribute("article") Article article) {
        articleService.simpanArtikel(article);
        return "redirect:/admin/artikel"; // Setelah simpan, balikkan ke halaman tabel admin
    }

    // 4. Menampilkan Form Edit yang Sudah Terisi Data Lama Berdasarkan ID
    @GetMapping("/artikel/edit/{id}")
    public String formEditArtikel(@PathVariable("id") Long id, Model model) {
        Article article = articleService.ambilArtikelBerdasarkanId(id);
        
        if (article == null) {
            return "redirect:/admin/artikel"; // Jika ID ngawur, balikkan ke tabel
        }

        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("pageTitle", "Edit Artikel");
        model.addAttribute("article", article); // Mengirim data lama ke form
        return "form-artikel"; // Menggunakan template form yang sama
    }

    // 5. Menghapus Artikel Berdasarkan ID
    @GetMapping("/artikel/hapus/{id}")
    public String hapusArtikel(@PathVariable("id") Long id) {
        articleService.hapusArtikelBerdasarkanId(id);
        return "redirect:/admin/artikel"; // Setelah hapus, balikkan ke halaman tabel admin
    }
}