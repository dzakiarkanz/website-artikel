package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.service.ArticleService;
import com.arkan.portalartikel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    private final String NAMA_WEB = "Tech Pulse";

    @GetMapping("/")
    public String isiBeranda(Model model) {
        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("articles", articleService.ambilSemuaArtikel());
        return "index";
    }

    @GetMapping("/article/{id}")
    public String detailArtikel(@PathVariable("id") String id, Model model) { 
        var article = articleService.ambilArtikelBerdasarkanId(id);
        if (article != null) {
            model.addAttribute("webName", NAMA_WEB);
            model.addAttribute("article", article);
            model.addAttribute("comments", commentService.ambilKomentarBerdasarkanArtikel(id));
            return "detail";
        }
        return "redirect:/";
    }
}