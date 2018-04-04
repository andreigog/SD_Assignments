package ro.utcluj.student.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.utcluj.student.business.HomeService;
import ro.utcluj.student.business.TournamentsService;
import ro.utcluj.student.dao.Model.Tournament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TournamentsController {
    private HomeService homeService = new HomeService();
    private TournamentsService tournamentsService = new TournamentsService();
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
        loadTournaments();
    }

    private void loadTournaments() {
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
        tournaments = homeService.getTournaments();
        Iterator iterator = tournaments.iterator();
        while (iterator.hasNext()) {
            Tournament tournament = (Tournament) iterator.next();
            cmbSelectTournament.setConverter(new StringConverter<Tournament>() {
                @Override
                public String toString(Tournament object) {
                    return object.getInfo() + "  " + object.getEmptySpots() + "/8";
                }

                @Override
                public Tournament fromString(String string) {
                    return null;
                }
            });
            cmbSelectTournament.getItems().add(tournament);
        }
    }

    private void loadTournamentInfo() {
        Tournament tournament = (Tournament) cmbSelectTournament.getValue();
        txtName.setText(tournament.getName());
        txtLocation.setText(tournament.getLocation());
        txtRegisteredPlayers.setText(String.valueOf(tournament.getEmptySpots()));
        txtValue.setText(String.valueOf(tournament.getValue()));
        txtYear.setText(String.valueOf(tournament.getYear()));
        txtId.setText(String.valueOf(tournament.getId()));
    }

    @FXML
    private void selectTournament() {
        loadTournamentInfo();
    }

    @FXML
    private void deleteTournament() {
        Tournament tournament = (Tournament) cmbSelectTournament.getValue();
        tournamentsService.delete(tournament);
        loadTournaments();
    }

    @FXML
    private void saveTournament() {
        Tournament tournament = (Tournament) cmbSelectTournament.getValue();
        tournament.setLocation(txtLocation.getText());
        tournament.setName(txtName.getText());
        tournament.setYear(Integer.parseInt(txtYear.getText()));
        tournament.setValue(Integer.parseInt(txtValue.getText()));
        tournamentsService.save(tournament);
        loadTournaments();
    }

    @FXML
    private void createTournament() {
        Tournament tournament = new Tournament(Integer.parseInt(txtId.getText()),
                txtName.getText(),
                Integer.parseInt(txtValue.getText()),
                txtLocation.getText(),
                Integer.parseInt(txtYear.getText()),
                Integer.parseInt(txtRegisteredPlayers.getText()));
        tournamentsService.create(tournament);
        loadTournaments();
    }
}
