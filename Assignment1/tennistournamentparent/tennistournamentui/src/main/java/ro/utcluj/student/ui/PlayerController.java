package ro.utcluj.student.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.utcluj.student.business.PlayerService;
import ro.utcluj.student.dao.Model.Player;
import ro.utcluj.student.dao.Model.Tournament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerController {
    private PlayerService playerService = new PlayerService();
    private Player player;
    private Integer userId;
    @FXML
    TextField txtName;
    @FXML
    TextField txtCountry;
    @FXML
    TextField txtAge;
    @FXML
    TextField txtGender;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    private AnchorPane root;
    @FXML
    ComboBox cmbTournaments;
    @FXML
    Button Enroll;

    public PlayerController(Integer userId) {
        this.player = playerService.getPlayer(userId);
        this.userId = userId;
    }

    @FXML
    public void initialize() {
        loadPlayerInfo();
        loadTournaments();
    }

    void loadPlayerInfo() {
        txtName.setText(player.getName());
        txtAge.setText(String.valueOf(player.getAge()));
        txtCountry.setText(player.getCountry());
        if (player.getGender() == 1)
            txtGender.setText("Male");
        else
            txtGender.setText("Female");
        txtUsername.setText(playerService.getUsername(userId));
        txtPassword.setText(playerService.getPassword(userId));
    }

    @FXML
    private void logout() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Login.fxml", null));
    }

    @FXML
    private void saveAccount() throws IOException {
        player.setName(txtName.getText());
        player.setCountry(txtCountry.getText());
        player.setAge(Integer.parseInt(txtAge.getText()));
        if (txtGender.getText().equals("Male"))
            player.setGender(1);
        else
            player.setGender(0);
        playerService.updatePlayer(player);
        //playerService.saveUsername(txtUsername.getText(),this.userId);
        // playerService.savePassword(txtPassword.getText(),this.userId);
    }

    @FXML
    private void deleteAccount() throws IOException {
    }

    private void loadTournaments() {
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
        tournaments = playerService.getTournaments();
        Iterator iterator = tournaments.iterator();
        while (iterator.hasNext()) {
            Tournament tournament = (Tournament) iterator.next();
            cmbTournaments.setConverter(new StringConverter<Tournament>() {
                @Override
                public String toString(Tournament object) {
                    if (playerService.checkIfRegistered(player, object))
                        return object.getInfo() + "  " + object.getEmptySpots() + "/8" + "  [registered]";
                    else
                        return object.getInfo() + "  " + object.getEmptySpots() + "/8";
                }

                @Override
                public Tournament fromString(String string) {
                    return null;
                }
            });
            cmbTournaments.getItems().add(tournament);
        }
    }

    @FXML
    private void cmbPressed() {

    }

    @FXML
    private void enroll() {
        Tournament tournament = (Tournament) cmbTournaments.getValue();
        if (playerService.checkIfRegistered(player, tournament)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You are already registered to that tournament!");

            alert.showAndWait();
        } else {
            if (tournament.getEmptySpots() < 8) {
                tournament.registerPlayer();
                playerService.registerPlayer(tournament);
                playerService.register(player, tournament);
                cmbTournaments.getItems().clear();
                loadTournaments();
            }

        }
    }
}
