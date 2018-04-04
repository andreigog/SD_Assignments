package ro.utcluj.student.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ro.utcluj.student.business.LoginService;

import java.io.IOException;

public class TennisTournamentController {
    private LoginService loginService=new LoginService();
    @FXML
    private TextField userNameTxtField;
    @FXML
    private TextField passwordTxtField;
    @FXML
    private ComboBox cmbBoxLogin;
    @FXML
    private AnchorPane root;

    @FXML
    public void initialize() {
        cmbBoxLogin.getItems().addAll("player",
                "organizer");
    }

    @FXML
    private void loginPressed() throws IOException {
        System.out.println(userNameTxtField.getText() + " - " + passwordTxtField.getText());

        if ((cmbBoxLogin.getValue()==null)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an user type!");

            alert.showAndWait();
        } else{
            Integer userValid = loginService.checkUser(userNameTxtField.getText(),
                    passwordTxtField.getText(),  (String) cmbBoxLogin.getValue());
            if (userValid!=null) {
                //change scene in current stage
                Stage currentStage = (Stage) root.getScene().getWindow();
                if (((String)cmbBoxLogin.getValue()).equals("organizer"))
                    currentStage.setScene(Utils.createScene("/layout/Home.fxml", userValid));
                else if (((String)cmbBoxLogin.getValue()).equals("player")){
                    currentStage.setScene(Utils.createScene("/layout/Player.fxml", userValid));
                }
            } else {
                //show a warning message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Username or password are wrong. Try again a different username with the proper password!");

                alert.showAndWait();
            }
        }
    }
}
