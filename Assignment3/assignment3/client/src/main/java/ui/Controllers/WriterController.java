package ui.Controllers;

import Mappers.Deserializer;
import Mappers.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Article;
import model.Writer;
import ui.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WriterController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtAbstract;
    @FXML
    private TextArea txtBody;
    @FXML
    private ComboBox cmbSelectArticle;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Writer writer;

    public WriterController(ObjectOutputStream outStream, ObjectInputStream inStream, Writer writer) {
        this.outStream = outStream;
        this.inStream = inStream;
        this.writer = writer;
    }

    @FXML
    public void initialize() {
        loadArticles();
        txtBody.setWrapText(true);
        cmbSelectArticle.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    updateArticle();
                }
        );
    }

    @FXML
    private void logout() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Login.fxml", outStream, inStream,null));
    }

    @FXML
    private void saveArticle(){
        try {
            Article article = new Article(txtTitle.getText(),txtAbstract.getText(),this.writer,txtBody.getText());
            outStream.writeObject("create_article\n"+ Serializer.articleToJson(article));
            outStream.flush();
            Boolean response = (Boolean) inStream.readObject();
            if (response)
                System.out.println("Article created successfully!");
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong when creating the new article!");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteArticle(){

    }

    private void loadArticles(){
        try {
            outStream.writeObject("get_writer_articles\n"+ Serializer.writerToJson(this.writer));
            outStream.flush();
            String response = (String) inStream.readObject();
            System.out.println(response);
            Article[] articles = Deserializer.getArticlesFromJSON(response);
            cmbSelectArticle.getItems().clear();
            for (int i = 0; i < articles.length; i++) {
                System.out.println(articles[i].getTitle());
                cmbSelectArticle.getItems().add(articles[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateArticle(){
        Article article = (Article) cmbSelectArticle.getValue();
        txtTitle.setText(article.getTitle());
        txtAbstract.setText(article.getDocumentAbstract());
        txtBody.setText(article.getBody());
    }

}
