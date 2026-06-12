package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.Article;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;

import static org.assertj.core.api.Assertions.assertThat;

class HomeControllerTest {

    @Test
    void homePopulatesExpectedModelAttributes() {
        HomeController controller = new HomeController();
        ExtendedModelMap model = new ExtendedModelMap();

        String viewName = controller.home(model);

        assertThat(viewName).isEqualTo("index");
        assertThat(model.containsAttribute("featuredArticles")).isTrue();
        assertThat(model.containsAttribute("popularCategories")).isTrue();
        assertThat(model.containsAttribute("latestArticle")).isTrue();

        @SuppressWarnings("unchecked")
        List<Article> featuredArticles = (List<Article>) model.getAttribute("featuredArticles");
        featuredArticles = Objects.requireNonNull(featuredArticles);

        assertThat(featuredArticles).hasSize(4);
        assertThat(featuredArticles.get(0).category()).isEqualTo("JAVA");
        assertThat(featuredArticles.get(0).title()).isEqualTo("Panduan Lengkap Spring Boot Bootstrap");
        assertThat(featuredArticles.get(1).author()).isEqualTo("Admin Tech");
        assertThat(featuredArticles.get(3).excerpt()).contains("template yang rapi dan konsisten");

        @SuppressWarnings("unchecked")
        List<String> popularCategories = (List<String>) model.getAttribute("popularCategories");
        popularCategories = Objects.requireNonNull(popularCategories);

        assertThat(popularCategories).containsExactly("Kategori", "Tech", "Tutorials", "AI & Dev");

        Article latestArticle = (Article) model.getAttribute("latestArticle");
        latestArticle = Objects.requireNonNull(latestArticle);

        assertThat(latestArticle.title()).isEqualTo("Membuat Website Artikel Sederhana");
        assertThat(latestArticle.publishedAt()).isEqualTo("10 Juni 2026");
    }
}