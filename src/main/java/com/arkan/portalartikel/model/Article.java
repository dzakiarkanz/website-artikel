package com.arkan.portalartikel.model;

public record Article(
        String category,
        String title,
        String excerpt,
        String author,
        String publishedAt
) {
}