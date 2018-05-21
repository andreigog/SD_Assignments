package Mappers;

import com.google.gson.Gson;
import model.Admin;
import model.Article;
import model.Writer;

import java.io.FileWriter;
import java.io.IOException;

public class Serializer {
    private static Gson gson = new Gson();

    public static void serializeArticle(Article article) {
        try {
            String jsonString = gson.toJson(article);
            FileWriter fileWriter = new FileWriter("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\articles\\"+article.getTitle()+".json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeWriter(Writer writer) {
        try {
            String jsonString = gson.toJson(writer);
            FileWriter fileWriter = new FileWriter("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\writers\\"+writer.getEmail()+".json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeAdmin(Admin admin) {
        try {
            String jsonString = gson.toJson(admin);
            FileWriter fileWriter = new FileWriter("C:\\Users\\andrei\\IdeaProjects\\assignment3\\commonresources\\src\\main\\resources\\admin.json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String writerToJson(Writer writer){
        return gson.toJson(writer);
    }

    public static String articleToJson(Article article){
        return gson.toJson(article);
    }

}
