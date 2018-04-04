package ro.utcluj.student.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ro.utcluj.student.business.HomeService;
import ro.utcluj.student.business.PlayerService;
import ro.utcluj.student.dao.Model.Match;
import ro.utcluj.student.dao.Model.Player;
import ro.utcluj.student.dao.Model.Score;
import ro.utcluj.student.dao.Model.Tournament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class HomeController {
    private HomeService homeService = new HomeService();
    private PlayerService playerService = new PlayerService();
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
    Label lblSet41;
    @FXML
    Label lblSet42;
    @FXML
    Label lblSet51;
    @FXML
    Label lblSet52;
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
        loadTournaments();
        loadPlayers();
    }

    @FXML
    private void pointPlayer1() throws IOException {
        Match currentMatch = (Match) cmbSelectGame.getValue();
        if (currentMatch != null && currentMatch.checkWinner() == null) {
            currentMatch.point(currentMatch.getPlayer1());
            updateGameScore(currentMatch);
            if (currentMatch.checkWinner() != null) {
                updateSeeds();
                resetLabels();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Game is already over!");

            alert.showAndWait();
        }
    }

    @FXML
    private void pointPlayer2() throws IOException {
        Match currentMatch = (Match) cmbSelectGame.getValue();
        if (currentMatch != null && currentMatch.checkWinner() == null) {
            currentMatch.point(currentMatch.getPlayer2());
            updateGameScore(currentMatch);
            if (currentMatch.checkWinner() != null) {
                updateSeeds();
                resetLabels();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Game is already over!");

            alert.showAndWait();
        }
    }

    @FXML
    private void selectGame() {
        Match currentMatch = (Match) cmbSelectGame.getValue();
        if (currentMatch != null) {
            lblPlayer1.setText(currentMatch.getPlayer1().getInfo());
            lblPlayer2.setText(currentMatch.getPlayer2().getInfo());
            updateGameScore(currentMatch);
        }
    }

    @FXML
    private void drawSeeds() {
        Tournament tournament = (Tournament) cmbSelectTournament.getValue();
        if (tournament.getEmptySpots() == 8) {
            ArrayList<Match> games = homeService.generateGames((Tournament) cmbSelectTournament.getValue()).getMatches();
            Label[] labels = new Label[]{lbl81, lbl82, lbl83, lbl84, lbl85, lbl86, lbl87, lbl88};
            for (int i = 0; i < 4; i++) {
                Match game = games.get(i);
                labels[i * 2].setText(game.getPlayer1().getInfo());
                labels[i * 2 + 1].setText(game.getPlayer2().getInfo());
            }
            loadGames();
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
        if (cmbSelectTournament.getValue()!=null) {
            Tournament tournament = (Tournament) cmbSelectTournament.getValue();
            lblTournamentName.setText(tournament.getInfo());
        }
    }

    @FXML
    private void saveTournament() {
        if (((Tournament) cmbSelectTournament.getValue()).getWinner() != null) {
            homeService.saveTournament((Tournament) cmbSelectTournament.getValue());
        }
    }

    @FXML
    private void editTournaments() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Tournaments.fxml", null));
    }

    @FXML
    private void editAccounts() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Accounts.fxml", null));
    }

    private void updateSeeds() {
        ArrayList<Match> games = homeService.updateGames((Tournament) cmbSelectTournament.getValue()).getMatches();
        ((Tournament) cmbSelectTournament.getValue()).setMatches(games);
        Label[] labels = new Label[]{lbl41, lbl42, lbl43, lbl44, lbl21, lbl22};
        for (int i = 0; i < 3; i++) {
            Match game = games.get(i + 4);
            if (game.getPlayer1() != null) {
                labels[i * 2].setText(game.getPlayer1().getInfo());
            }
            if (game.getPlayer2() != null) {
                labels[i * 2 + 1].setText(game.getPlayer2().getInfo());
            }
        }
        if (games.get(6).getPlayer1() != null && games.get(6).getPlayer2() != null && games.get(6).checkWinner() != null) {
            lbl11.setText(games.get(6).checkWinner().getInfo());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner Winner, Chicken Dinner!");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations to " + games.get(6).checkWinner().getInfo() + " for winning the " + ((Tournament) cmbSelectTournament.getValue()).getInfo());

            alert.showAndWait();
            ((Tournament) cmbSelectTournament.getValue()).setWinner(games.get(6).checkWinner());
        }
        loadGames();
    }

    private void loadTournaments() {
        cmbSelectTournament.getItems().clear();
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

    private void loadGames() {
        cmbSelectGame.getItems().clear();
        ArrayList<Match> games = ((Tournament) cmbSelectTournament.getValue()).getMatches();
        Iterator iterator = games.iterator();
        while (iterator.hasNext()) {
            Match match = (Match) iterator.next();
            if (match.checkWinner() == null && match.getPlayer1() != null && match.getPlayer2() != null) {
                cmbSelectGame.setConverter(new StringConverter<Match>() {
                    @Override
                    public String toString(Match object) {
                        return object.getPlayer1().getName() + " vs " + object.getPlayer2().getName();
                    }

                    @Override
                    public Match fromString(String string) {
                        return null;
                    }
                });
                cmbSelectGame.getItems().add(match);
            }
        }
    }

    private void resetLabels() {
        Label[] labels = new Label[]{lblSet11, lblSet12, lblSet21, lblSet22, lblSet31, lblSet32, lblSet41, lblSet42, lblSet51, lblSet52};
        for (int i = 0; i < 5; i++) {
            labels[i * 2].setStyle("-fx-font-weight: normal");
            labels[i * 2 + 1].setStyle("-fx-font-weight: normal");
        }
    }

    private void updateGameScore(Match match) {
        Score gameScore = match.getGameScore();
        Score setScore = match.getSetScore();
        Score[] overallScore = match.getOverallScore();
        Label[] labels = new Label[]{lblSet11, lblSet12, lblSet21, lblSet22, lblSet31, lblSet32, lblSet41, lblSet42, lblSet51, lblSet52};
        lblGame1.setText("    " + gameScore.getValue1());
        lblGame2.setText("    " + gameScore.getValue2());
        for (int i = 0; i < 5; i++) {
            labels[match.getCurrentSet() * 2].setStyle("-fx-font-weight: bold");
            labels[match.getCurrentSet() * 2 + 1].setStyle("-fx-font-weight: bold");
            labels[i * 2].setText(overallScore[i].getValue1() + "");
            labels[i * 2 + 1].setText(overallScore[i].getValue2() + "");
            if (gameScore.getValue1() == 40 && gameScore.getValue2() == 40) {
                if (match.checkAdvantage() == null) {
                    lblGame1.setText("    " + 40);
                    lblGame2.setText("    " + 40);
                } else if (match.checkAdvantage() == match.getPlayer1())
                    lblGame1.setText("   A");
                else
                    lblGame2.setText("   A");
            }
        }

    }

    private void loadPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        players = homeService.getPlayers();
        Iterator iterator = players.iterator();
        while (iterator.hasNext()) {
            Player player = (Player) iterator.next();
            cmbSelectPlayer.setConverter(new StringConverter<Player>() {
                @Override
                public String toString(Player object) {
                    return object.getInfo();
                }

                @Override
                public Player fromString(String string) {
                    return null;
                }
            });
            cmbSelectPlayer.getItems().add(player);
        }
    }

    @FXML
    private void addPlayer() {
        if (cmbSelectTournament.getValue() != null && cmbSelectPlayer.getValue() != null) {
            Tournament tournament = (Tournament) cmbSelectTournament.getValue();
            Player player = (Player) cmbSelectPlayer.getValue();
            if (playerService.checkIfRegistered(player, tournament)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("The player is already registered to that tournament!");

                alert.showAndWait();
            } else {
                if (tournament.getEmptySpots() < 8) {
                    tournament.registerPlayer();
                    playerService.registerPlayer(tournament);
                    playerService.register(player, tournament);
                    loadTournaments();
                }
            }
        }
    }

    @FXML
    private void logout() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Login.fxml", null));
    }
}
