package ui.Controllers;

import Mappers.Deserializer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AccessRight;
import model.Writer;
import ui.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginController {
    @FXML
    private TextField userNameTxtField;
    @FXML
    private TextField passwordTxtField;
    @FXML
    private ComboBox cmbBoxLogin;
    @FXML
    private AnchorPane root;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public LoginController(ObjectOutputStream outStream, ObjectInputStream inStream) {
        this.outStream = outStream;
        this.inStream = inStream;
    }

    @FXML
    public void initialize() {
        loadComboBox();
        cmbBoxLogin.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    checkAccessRight();
                }
        );
    }

    @FXML
    private void loginPressed() throws IOException {
        AccessRight accessRight = (AccessRight) cmbBoxLogin.getValue();
        try {
            if (accessRight.ordinal()==AccessRight.READER.ordinal()){
                Stage currentStage = (Stage) root.getScene().getWindow();
                currentStage.setScene(Utils.createScene("/layout/Reader.fxml",outStream,inStream,null));
            }else {
                outStream.writeObject("login\n" + userNameTxtField.getText() + "\n" + passwordTxtField.getText() + "\n" + accessRight);
                outStream.flush();
                String response = (String) inStream.readObject();
                String[] args = response.split("\n");
                switch (args[0]) {
                    case "admin": {
                        Stage currentStage = (Stage) root.getScene().getWindow();
                        currentStage.setScene(Utils.createScene("/layout/Admin.fxml", outStream, inStream, null));
                        break;
                    }
                    case "writer": {
                        Writer writer = Deserializer.getWriterFromJSON(args[1]);
                        Stage currentStage = (Stage) root.getScene().getWindow();
                        currentStage.setScene(Utils.createScene("/layout/Writer.fxml", outStream, inStream, writer));
                        break;
                    }
                    case "failed": {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("User not found or wrong password!");
                        alert.showAndWait();
                        break;
                    }
                    default: {
                        System.out.println("default case");
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void checkAccessRight() {
        AccessRight accessRight = (AccessRight) cmbBoxLogin.getValue();
        if (accessRight.ordinal() == AccessRight.READER.ordinal()) {
            passwordTxtField.setDisable(true);
            userNameTxtField.setDisable(true);
        } else {
            passwordTxtField.setDisable(false);
            userNameTxtField.setDisable(false);
        }
    }

    private void loadComboBox() {
        cmbBoxLogin.getItems().addAll(AccessRight.ADMIN,
                AccessRight.READER, AccessRight.WRITER);
    }


}
