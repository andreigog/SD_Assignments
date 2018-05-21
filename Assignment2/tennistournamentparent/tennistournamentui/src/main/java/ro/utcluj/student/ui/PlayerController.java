package ro.utcluj.student.ui;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ro.utcluj.student.business.entities.PlayerEntity;
import ro.utcluj.student.business.entities.TournamentEntity;
import ro.utcluj.student.business.entities.UserEntity;
import ro.utcluj.student.business.services.ServiceProvider;
import ro.utcluj.student.dao.hibdao.HibernateAnnotationUtil;

import java.io.IOException;
import java.util.List;

public class PlayerController {
    private ServiceProvider serviceProvider;
    private PlayerEntity player;
    private TournamentEntity tournament;
    private String category;


    private UserEntity user;
    @FXML
    private AnchorPane root;
    @FXML
    TextField txtName;
    @FXML
    TextField txtCountry;
    @FXML
    TextField txtAge;
    @FXML
    TextField txtUsername;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtSearch;
    @FXML
    TextField txtBalance;
    @FXML
    ComboBox cmbCategory;
    @FXML
    Button Enroll;
    @FXML
    ListView listView;

    public PlayerController(UserEntity user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        serviceProvider = new ServiceProvider();
        HibernateAnnotationUtil.getSessionFactory();
        loadPlayerInfo();
        loadCategories();
        loadTournaments();
        this.category = "All";
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> searchTournaments(txtSearch.getText()));
        listView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<TournamentEntity>) (observable, oldValue, newValue) -> setTournament(newValue));
        cmbCategory.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    setCategory((String) newValue);
                }
        );

    }

    void loadPlayerInfo() {
        this.player = serviceProvider.getPlayerService().getPlayer(this.user);
        txtName.setText(player.getName());
        txtAge.setText(String.valueOf(player.getAge()));
        txtCountry.setText(player.getCountry());
        txtUsername.setText(this.user.getUsername());
        txtPassword.setText(this.user.getPassword());
        txtBalance.setText(String.valueOf(player.getBalance()));
    }

    @FXML
    private void logout() throws IOException {
        Stage currentStage = (Stage) root.getScene().getWindow();
        currentStage.setScene(Utils.createScene("/layout/Login.fxml", null));
    }

    private void searchTournaments(String name) {
        List<TournamentEntity> tournaments = serviceProvider.getPlayerService().getTournaments(name, this.category);
        listView.getItems().clear();
        for (int i = 0; i < tournaments.size(); i++) {
            listView.getItems().add(tournaments.get(i));
        }
    }

    @FXML
    private void saveAccount() throws IOException {
    }

    @FXML
    private void deleteAccount() throws IOException {
    }

    @FXML
    private void enroll() {
        serviceProvider.getPlayerService().enroll(this.player, this.tournament);
        loadPlayerInfo();
        loadTournaments();
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    private void loadCategories() {
        cmbCategory.getItems().add("All");
        cmbCategory.getItems().add("Paid");
        cmbCategory.getItems().add("Free");
        cmbCategory.getItems().add("Upcoming");
        cmbCategory.getItems().add("Ongoing");
    }

    public void setCategory(String category) {
        this.category = category;
        searchTournaments(txtSearch.getText());
    }

    private void loadTournaments() {
        listView.getItems().clear();
        List<TournamentEntity> tournaments = serviceProvider.getPlayerService().getAllTournaments();
        for (int i = 0; i < tournaments.size(); i++) {
            listView.getItems().add(tournaments.get(i));
        }
    }

}
