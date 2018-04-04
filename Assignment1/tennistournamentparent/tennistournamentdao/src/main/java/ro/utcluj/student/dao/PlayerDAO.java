package ro.utcluj.student.dao;

import ro.utcluj.student.dao.Model.Player;
import ro.utcluj.student.dao.Model.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDAO {
    protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private final static String updatePlayerStatementString = "UPDATE Players SET Name=?,Country=?,Age=?,Gender=? WHERE PlayerId=?";
    private final static String checkRegisteredStatementString = "SELECT * FROM tournamentplayers";
    private  final static String insertStatementString = "INSERT INTO tournamentplayers (PlayerId,TournamentId) VALUES (?,?)";
    private final static String viewAllStatementString = "SELECT * FROM Players";

    public static void updatePlayer(Player player) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        try {
            findPlayersStatement = dbConnection.prepareStatement(updatePlayerStatementString);
            findPlayersStatement.setString(1, player.getName());
            findPlayersStatement.setString(2, player.getCountry());
            findPlayersStatement.setInt(3, player.getAge());
            findPlayersStatement.setInt(4, player.getGender());
            findPlayersStatement.setInt(5, player.getId());
            findPlayersStatement.executeUpdate();
            rs = findPlayersStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:getPlayer " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static boolean checkIfRegistered(Player player, Tournament tournament){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(checkRegisteredStatementString);
            rs = findStatement.executeQuery();

            while (rs.next()) {
                if (player.getId()==rs.getInt("PlayerId") && tournament.getId()==rs.getInt("TournamentId"))
                    return  true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PlayerDAO:viewAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return false;
    }

    public static void register(Player  player, Tournament tournament){
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, player.getId());
            insertStatement.setInt(2, tournament.getId());
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static ArrayList<Player> viewAll() {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            findStatement = dbConnection.prepareStatement(viewAllStatementString);
            rs = findStatement.executeQuery();

            while (rs.next()) {
                Player player = new Player(rs.getInt("PlayerId"),
                        rs.getString("Name"),
                        rs.getString("Country"),
                        rs.getInt("Age"),
                        rs.getInt("Gender"),
                        rs.getInt("Ranking"));
                players.add(player);
            }
            return players;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PlayerDAO:viewAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }
}
