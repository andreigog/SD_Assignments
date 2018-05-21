package ui.Controllers;

import Mappers.Deserializer;
import Mappers.Serializer;
import javafx.fxml.FXML;
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

public class ReaderController {
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
    @FXML
    private ComboBox cmbSelectAuthor;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Writer writer;

    public ReaderController(ObjectOutputStream outStream, ObjectInputStream inStream) {
        this.outStream = outStream;
        this.inStream = inStream;
    }


    @FXML
    public void initialize() {
        txtTitle.editableProperty().setValue(false);
        txtAbstract.editableProperty().setValue(false);
        txtBody.editableProperty().setValue(false);
        txtBody.setWrapText(true);
        loadWriters();
        cmbSelectArticle.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    updateArticle();
                }
        );
        cmbSelectAuthor.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    updateAuthor();
                }
        );
    }

    @FXML
    private void logout() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Login.fxml", outStream, inStream,null));
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
    private void loadWriters(){
        try {
            outStream.writeObject("get_writers");
            outStream.flush();
            String response = (String) inStream.readObject();
            Writer[] writers = Deserializer.getWritersFromJSON(response);
            cmbSelectAuthor.getItems().clear();
            for (int i = 0; i < writers.length; i++) {
                cmbSelectAuthor.getItems().add(writers[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void updateAuthor(){
        Writer writerTemp = (Writer) cmbSelectAuthor.getValue();
        System.out.println(writerTemp.getName());
        this.writer=writerTemp;
        loadArticles();
    }
}
