package model;

import java.util.List;

public class Article {
    private String title;
    private String documentAbstract;
    private Writer author;
    private String body;
    private List<Article> relatedArticles;

    public Article(String title, String documentAbstract, Writer author, String body) {
        this.title = title;
        this.documentAbstract = documentAbstract;
        this.author = author;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocumentAbstract() {
        return documentAbstract;
    }

    public void setDocumentAbstract(String documentAbstract) {
        this.documentAbstract = documentAbstract;
    }

    public Writer getAuthor() {
        return author;
    }

    public void setAuthor(Writer author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Article> getRelatedArticles() {
        return relatedArticles;
    }

    public void setRelatedArticles(List<Article> relatedArticles) {
        this.relatedArticles = relatedArticles;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
