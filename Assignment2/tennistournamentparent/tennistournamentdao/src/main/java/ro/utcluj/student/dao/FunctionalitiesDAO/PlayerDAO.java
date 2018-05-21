package ro.utcluj.student.dao.FunctionalitiesDAO;


import ro.utcluj.student.dao.model.Player;
import ro.utcluj.student.dao.model.User;

import java.util.List;

public interface PlayerDAO {

    Player createPlayer(Player player);

    void updatePlayer(Player player);

    List<Player> getAllPlayers();

    Player getUserPlayer(User user);

    void deletePlayer(Player player);

}
