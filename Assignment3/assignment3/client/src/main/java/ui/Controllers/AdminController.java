package ui.Controllers;

import Mappers.Deserializer;
import Mappers.Serializer;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Writer;
import ui.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private ListView listWriters;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public AdminController(ObjectOutputStream outStream, ObjectInputStream inStream) {
        this.outStream = outStream;
        this.inStream = inStream;
    }

    @FXML
    public void initialize() {
        loadWriters();
        listWriters.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Writer>) (observable, oldValue, newValue) -> updateWriter(newValue));

    }

    @FXML
    private void logout() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Login.fxml", outStream, inStream,null));
    }

    @FXML
    private void saveAccount(){
        Writer writer = new Writer(txtName.getText(),txtEmail.getText(),txtPassword.getText());
        String jsonString = Serializer.writerToJson(writer);
        try {
            outStream.writeObject("create_writer\n"+jsonString);
            outStream.flush();
            Boolean response = (Boolean) inStream.readObject();
            if (response){
                System.out.println("Successfully created the new writer");
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong when creating the new writer!");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void deleteAccount(){

    }

    private void loadWriters(){
        try {
            outStream.writeObject("get_writers");
            outStream.flush();
            String response = (String) inStream.readObject();
            Writer[] writers = Deserializer.getWritersFromJSON(response);
            listWriters.getItems().clear();
            for (int i = 0; i < writers.length; i++) {
                listWriters.getItems().add(writers[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateWriter(Writer writer){
        txtName.setText(writer.getName());
        txtPassword.setText(writer.getPassword());
        txtEmail.setText(writer.getEmail());
    }

}
