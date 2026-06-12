package com.arkan.portalartikel.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    @Test
    void recordExposesAllValues() {
        Article article = new Article(
                "JAVA",
                "Spring Boot 3.3",
                "Testing the record accessors",
                "Dzaki Arkan",
                "10 Juni 2026");

        assertThat(article.category()).isEqualTo("JAVA");
        assertThat(article.title()).isEqualTo("Spring Boot 3.3");
        assertThat(article.excerpt()).isEqualTo("Testing the record accessors");
        assertThat(article.author()).isEqualTo("Dzaki Arkan");
        assertThat(article.publishedAt()).isEqualTo("10 Juni 2026");
    }
}