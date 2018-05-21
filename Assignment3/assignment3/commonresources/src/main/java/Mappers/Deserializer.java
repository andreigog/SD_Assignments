package Mappers;

import com.google.gson.Gson;
import model.Admin;
import model.Article;
import model.Writer;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {

    private static Gson gson = new Gson();

    public static Article getArticleFromFile(String title) {
        Article article = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\articles\\" + title + ".json");
            article = gson.fromJson(fileReader, Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return article;
    }

    public static Article getArticleFromJSON(String jsonString) {
        Article article = null;
        try {
            article = gson.fromJson(jsonString, Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return article;
    }

    public static Writer getWriterFromJSON(String jsonString) {
        Writer writer = null;
        try {
            writer = gson.fromJson(jsonString, Writer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer;
    }

    public static Admin getAdminFromFile() {
        Admin admin = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\admin.json");
            admin = gson.fromJson(fileReader, Admin.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static Writer getWriterFromFile(String name) {
        Writer writer = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\writers\\" + name + ".json");
            writer = gson.fromJson(fileReader, Writer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer;
    }

    public static Writer getWriterFromSpecificFile(String name) {
        Writer writer = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\writers\\" + name);
            writer = gson.fromJson(fileReader, Writer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer;
    }

    public static Article getArticleFromSpecificFile(String name) {
        Article article = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\articles\\" + name);
            article = gson.fromJson(fileReader, Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    public static String getWritersFromFile() {
        File folder = new File("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\writers");
        File[] listOfFiles = folder.listFiles();
        List<Writer> writers = new ArrayList<>();
        for (File file : listOfFiles) {
            writers.add(getWriterFromSpecificFile(file.getName()));
        }
        return gson.toJson(writers);
    }

    public static String getArticlesFromFile(Writer writer) {
        File folder = new File("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\articles");
        File[] listOfFiles = folder.listFiles();
        List<Article> articles = new ArrayList<>();
        for (File file : listOfFiles) {
            Article tempArticle = getArticleFromSpecificFile(file.getName());
            if (writer.getName().equals(tempArticle.getAuthor().getName())){
                System.out.println(tempArticle.getTitle());
                articles.add(tempArticle);
            }
        }
        return gson.toJson(articles);
    }

    public static Writer[] getWritersFromJSON(String jsonString) {
        Writer[] writers = gson.fromJson(jsonString, Writer[].class);
        return writers;
    }

    public static Article[] getArticlesFromJSON(String jsonString) {
        Article[] articles = gson.fromJson(jsonString, Article[].class);
        return articles;
    }

}
