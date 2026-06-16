package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.Article;
import com.arkan.portalartikel.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    private final String NAMA_WEB = "Tech Pulse";

    @GetMapping("/artikel")
    public String kelolaArtikel(Model model) {
        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("articles", articleService.ambilSemuaArtikel());
        return "kelola-artikel";
    }

    @GetMapping("/artikel/tambah")
    public String formTambahArtikel(Model model) {
        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("pageTitle", "Tambah Artikel Baru");
        model.addAttribute("article", new Article()); 
        return "form-artikel";
    }

    @PostMapping("/artikel/simpan")
    public String simpanArtikel(@ModelAttribute("article") Article article) {
        articleService.simpanArtikel(article);
        return "redirect:/admin/artikel";
    }

    @GetMapping("/artikel/edit/{id}")
    public String formEditArtikel(@PathVariable("id") String id, Model model) { 
        Article article = articleService.ambilArtikelBerdasarkanId(id);
        if (article == null) {
            return "redirect:/admin/artikel";
        }
        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("pageTitle", "Edit Artikel");
        model.addAttribute("article", article);
        return "form-artikel";
    }

    @GetMapping("/artikel/hapus/{id}")
    public String hapusArtikel(@PathVariable("id") String id) { 
        articleService.hapusArtikelBerdasarkanId(id);
        return "redirect:/admin/artikel";
    }
}