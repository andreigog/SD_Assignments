package ro.utcluj.student.business;

import ro.utcluj.student.dao.*;
import ro.utcluj.student.dao.Model.Player;
import ro.utcluj.student.dao.Model.Tournament;

import java.util.ArrayList;

public class PlayerService {
    public Player getPlayer(Integer UserId) {
        Player player = UserDAO.getPlayer(UserId);
        return player;
    }

    public String getUsername(Integer UserId) {
        return UserDAO.getUsername(UserId);
    }

    public String getPassword(Integer UserId) {
        return UserDAO.getPassword(UserId);
    }

    public void updatePlayer(Player player) {
        PlayerDAO.updatePlayer(player);
    }

    public ArrayList<Tournament> getTournaments() {
        ArrayList<Tournament> tournaments;
        tournaments = TournamentDAO.viewAll();
        return tournaments;
    }

    public boolean checkIfRegistered(Player player, Tournament tournament) {
        return PlayerDAO.checkIfRegistered(player, tournament);
    }

    public void register(Player player, Tournament tournament) {
        PlayerDAO.register(player, tournament);
    }

    public void registerPlayer(Tournament tournament) {
        TournamentDAO.registerPlayer(tournament);
    }
}
