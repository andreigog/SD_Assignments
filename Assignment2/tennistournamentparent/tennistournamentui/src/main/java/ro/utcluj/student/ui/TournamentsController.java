package ro.utcluj.student.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ro.utcluj.student.business.services.AdminService;
import ro.utcluj.student.business.services.TournamentsService;

import java.io.IOException;

public class TournamentsController {
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox cmbSelectTournament;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField txtValue;
    @FXML
    private TextField txtRegisteredPlayers;
    @FXML
    private TextField txtWinner;
    @FXML
    private TextField txtId;

    @FXML
    private void back() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Home.fxml", null));
    }

    @FXML
    public void initialize() {

    }

    private void loadTournaments() {

    }

    private void loadTournamentInfo() {
    }

    @FXML
    private void selectTournament() {
    }

    @FXML
    private void deleteTournament() {

    }

    @FXML
    private void saveTournament() {

    }

    @FXML
    private void createTournament() {

    }
}
