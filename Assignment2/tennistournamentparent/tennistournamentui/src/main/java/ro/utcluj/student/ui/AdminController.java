package ro.utcluj.student.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.utcluj.student.business.entities.MatchEntity;
import ro.utcluj.student.business.entities.PlayerEntity;
import ro.utcluj.student.business.entities.ScoreEntity;
import ro.utcluj.student.business.entities.TournamentEntity;
import ro.utcluj.student.business.services.ServiceProvider;
import ro.utcluj.student.dao.hibdao.HibernateAnnotationUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AdminController {
    private ServiceProvider serviceProvider;
    @FXML
    ComboBox cmbSelectGame;
    @FXML
    ComboBox cmbSelectTournament;
    @FXML
    Label lblTournamentName;
    @FXML
    Label lblPlayer1;
    @FXML
    Label lblPlayer2;
    @FXML
    Label lbl81;
    @FXML
    Label lbl82;
    @FXML
    Label lbl83;
    @FXML
    Label lbl84;
    @FXML
    Label lbl85;
    @FXML
    Label lbl86;
    @FXML
    Label lbl87;
    @FXML
    Label lbl88;
    @FXML
    Label lbl41;
    @FXML
    Label lbl42;
    @FXML
    Label lbl43;
    @FXML
    Label lbl44;
    @FXML
    Label lbl21;
    @FXML
    Label lbl22;
    @FXML
    Label lbl11;
    @FXML
    Label lblSet11;
    @FXML
    Label lblSet12;
    @FXML
    Label lblSet21;
    @FXML
    Label lblSet22;
    @FXML
    Label lblSet31;
    @FXML
    Label lblSet32;
    @FXML
    Label lblGame1;
    @FXML
    Label lblGame2;
    @FXML
    ComboBox cmbSelectPlayer;
    @FXML
    private AnchorPane root;


    @FXML
    public void initialize() {
        serviceProvider = new ServiceProvider();
        HibernateAnnotationUtil.getSessionFactory();
        loadTournaments();
        loadPlayers();
        cmbSelectGame.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    updateGameScore();
                }
        );
    }

    @FXML
    private void enrollPlayer() {
        if (cmbSelectTournament.getValue() != null && cmbSelectPlayer.getValue() != null) {
            TournamentEntity tournament = (TournamentEntity) cmbSelectTournament.getValue();
            PlayerEntity player = (PlayerEntity) cmbSelectPlayer.getValue();
            if (serviceProvider.getAdminService().checkIfRegistered(player, tournament)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("The player is already registered to that tournament!");

                alert.showAndWait();
            } else {
                if (tournament.getFee() > player.getBalance()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Insufficient player funds!");
                } else if (tournament.getRegisteredPlayers() < 8) {
                    serviceProvider.getAdminService().enroll(player, tournament);
                    loadTournaments();
                }
            }
        }
    }

    @FXML
    private void pointPlayer1() throws IOException {
        MatchEntity matchEntity = (MatchEntity) cmbSelectGame.getValue();
        matchEntity = serviceProvider.getAdminService().pointPlayer1(matchEntity);
        updateGameScore();
        TournamentEntity tournament = (TournamentEntity) cmbSelectTournament.getValue();
        if (serviceProvider.getAdminService().checkIfFinished(matchEntity) != null) {
            serviceProvider.getAdminService().updateTournamentMatches(tournament);
            loadGames();
        }
        PlayerEntity winner = serviceProvider.getAdminService().checkIfFinishedTournament(tournament);
        if (winner != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner!");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations to "+winner.getName()
                    +" for winning "+tournament.getName()+" and going home with the prize of "+tournament.getFee()*tournament.getRegisteredPlayers());

            alert.showAndWait();
            lbl11.setText(winner.getName());
        }
    }

    @FXML
    private void pointPlayer2() throws IOException {
        MatchEntity matchEntity = (MatchEntity) cmbSelectGame.getValue();
        matchEntity = serviceProvider.getAdminService().pointPlayer2(matchEntity);
        updateGameScore();
        TournamentEntity tournament = (TournamentEntity) cmbSelectTournament.getValue();
        if (serviceProvider.getAdminService().checkIfFinished(matchEntity) != null) {
            serviceProvider.getAdminService().updateTournamentMatches(tournament);
            loadGames();
        }
        PlayerEntity winner = serviceProvider.getAdminService().checkIfFinishedTournament(tournament);
        if (winner != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner!");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations to "+winner.getName()
                    +" for winning "+tournament.getName()+" and going home with the prize of "+tournament.getFee()*tournament.getRegisteredPlayers());

            alert.showAndWait();
            lbl11.setText(winner.getName());
        }
    }

    @FXML
    private void selectGame() {
        MatchEntity currentMatch = (MatchEntity) cmbSelectGame.getValue();
        if (currentMatch != null) {
            lblPlayer1.setText(currentMatch.getPlayer1().toString());
            lblPlayer2.setText(currentMatch.getPlayer2().toString());
            updateGameScore();
        }
    }

    @FXML
    private void loadSeeds() {
        updateSeeds();
        loadGames();
    }

    @FXML
    private void drawSeeds() {
        TournamentEntity tournament = (TournamentEntity) cmbSelectTournament.getValue();
        if (tournament.getRegisteredPlayers() == 8) {
            serviceProvider.getAdminService().generateMatches(tournament);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Not enough registered players!");

            alert.showAndWait();
        }
    }

    @FXML
    private void selectTournament() {
        if (cmbSelectTournament.getValue() != null) {
            TournamentEntity tournament = (TournamentEntity) cmbSelectTournament.getValue();
            lblTournamentName.setText(tournament.getName());
            initializeSeeds();
            loadGames();
        }
    }

    @FXML
    private void saveTournament() {
    }

    @FXML
    private void editTournaments() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Tournaments.fxml", null));
    }

    @FXML
    private void editAccounts() throws IOException {
        /*Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Accounts.fxml", null));*/
    }

    @FXML
    private void logout() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Login.fxml", null));
    }

    private void initializeSeeds() {
        Label[] labels = new Label[]{lbl81, lbl82, lbl83, lbl84, lbl85, lbl86, lbl87, lbl88, lbl41, lbl42, lbl43, lbl44, lbl21, lbl22};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText("unseeded");
        }
    }

    private void updateSeeds() {
        TournamentEntity tournament = (TournamentEntity) cmbSelectTournament.getValue();
        List<MatchEntity> matches = serviceProvider.getAdminService().getAllTournamentMatches(tournament);
        Label[] labels = new Label[]{lbl81, lbl82, lbl83, lbl84, lbl85, lbl86, lbl87, lbl88, lbl41, lbl42, lbl43, lbl44, lbl21, lbl22};
        for (int i = 0; i < matches.size(); i++) {
            MatchEntity match = matches.get(i);
            if (match.getPlayer1() != null) {
                labels[i * 2].setText(match.getPlayer1().toString());
            } else {
                labels[i * 2].setText("unseeded");
            }
            if (match.getPlayer2() != null) {
                labels[i * 2 + 1].setText(match.getPlayer2().toString());
            } else {
                labels[i * 2 + 1].setText("unseeded");
            }
        }
        loadGames();
    }

    private void loadTournaments() {
        cmbSelectTournament.getItems().clear();
        List<TournamentEntity> tournamentsEntities;
        tournamentsEntities = serviceProvider.getAdminService().getAllTournaments();
        Iterator iterator = tournamentsEntities.iterator();
        while (iterator.hasNext()) {
            TournamentEntity tournamentEntity = (TournamentEntity) iterator.next();
            cmbSelectTournament.setConverter(new StringConverter<TournamentEntity>() {
                @Override
                public String toString(TournamentEntity object) {
                    return object.toString();
                }

                @Override
                public TournamentEntity fromString(String string) {
                    return null;
                }
            });
            cmbSelectTournament.getItems().add(tournamentEntity);
        }
    }

    private void loadPlayers() {
        List<PlayerEntity> players;
        players = serviceProvider.getAdminService().getAllPlayers();
        Iterator iterator = players.iterator();
        while (iterator.hasNext()) {
            PlayerEntity player = (PlayerEntity) iterator.next();
            cmbSelectPlayer.setConverter(new StringConverter<PlayerEntity>() {
                @Override
                public String toString(PlayerEntity object) {
                    return object.toString();
                }

                @Override
                public PlayerEntity fromString(String string) {
                    return null;
                }
            });
            cmbSelectPlayer.getItems().add(player);
        }
    }

    private void loadGames() {
        cmbSelectGame.getItems().clear();
        TournamentEntity tournament = (TournamentEntity) cmbSelectTournament.getValue();
        List<MatchEntity> matches = serviceProvider.getAdminService().getAllTournamentMatches(tournament);
        Iterator iterator = matches.iterator();
        while (iterator.hasNext()) {
            MatchEntity match = (MatchEntity) iterator.next();
            if (match.getPlayer1() != null && match.getPlayer2() != null) {
                cmbSelectGame.setConverter(new StringConverter<MatchEntity>() {
                    @Override
                    public String toString(MatchEntity object) {
                        return object.getPlayer1().getName() + " vs " + object.getPlayer2().getName();
                    }

                    @Override
                    public MatchEntity fromString(String string) {
                        return null;
                    }
                });
                cmbSelectGame.getItems().add(match);
            }
        }
    }

    private void updateGameScore() {
        MatchEntity matchEntity = (MatchEntity) cmbSelectGame.getValue();
        ScoreEntity scoreEntity = matchEntity.getScore();
        lblSet11.setText(String.valueOf(scoreEntity.getSet1Player1()));
        lblSet12.setText(String.valueOf(scoreEntity.getSet1Player2()));
        lblSet21.setText(String.valueOf(scoreEntity.getSet2Player1()));
        lblSet22.setText(String.valueOf(scoreEntity.getSet2Player2()));
        lblSet31.setText(String.valueOf(scoreEntity.getSet3Player1()));
        lblSet32.setText(String.valueOf(scoreEntity.getSet3Player2()));
        if (scoreEntity.getAdvantage()==0) {
            lblGame1.setText(String.valueOf(scoreEntity.getGamePlayer1()));
            lblGame2.setText(String.valueOf(scoreEntity.getGamePlayer2()));
        }else if (scoreEntity.getAdvantage()==1){
            lblGame1.setText("A");
            lblGame2.setText(String.valueOf(scoreEntity.getGamePlayer2()));
        }else if (scoreEntity.getAdvantage()==2){
            lblGame1.setText(String.valueOf(scoreEntity.getGamePlayer1()));
            lblGame2.setText("A");
        }
    }


}
