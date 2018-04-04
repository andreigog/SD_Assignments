package ro.utcluj.student.dao;


import ro.utcluj.student.dao.Model.Player;
import ro.utcluj.student.dao.Model.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TournamentDAO {

    protected static final Logger LOGGER = Logger.getLogger(TournamentDAO.class.getName());
    private final static String viewAllStatementString = "SELECT * FROM Tournament";
    private final static String getTournamentPlayersStatementString = "SELECT * FROM Players INNER JOIN tournamentplayers on tournamentplayers.PlayerId" +
            "=Players.PlayerId WHERE tournamentplayers.TournamentId=?";
    private final static String setWinnerStatementString = "UPDATE Tournament SET WinnerId=? WHERE TournamentId=?";
    private final static String registerPlayerStatementString = "UPDATE Tournament SET RegisteredPlayers=? WHERE TournamentId=?";
    private final static String findPlayerByNameStatementString = "SELECT PlayerId FROM Players WHERE name=?";
    private final static String createStatementString = "INSERT INTO Tournament (TournamentId,Name,Location,Year,Value,RegisteredPlayers) VALUES (?,?,?,?,?,?)";
    private final static String updateStatementString = "UPDATE Tournament SET Name=?, Location=?, Year=?, Value=? WHERE TournamentId=?";
    private final static String deleteStatementString = "DELETE FROM Tournament WHERE TournamentId=?";

    public static ArrayList<Tournament> viewAll() {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
        try {
            findStatement = dbConnection.prepareStatement(viewAllStatementString);
            rs = findStatement.executeQuery();

            while (rs.next()) {
                Tournament tournament = new Tournament(rs.getInt("TournamentId"),
                        rs.getString("Name"),
                        rs.getInt("Value"),
                        rs.getString("Location"),
                        rs.getInt("Year"),
                        rs.getInt("RegisteredPlayers"));
                ArrayList<Player> players = getTournamentPlayers(rs.getInt("TournamentId"));
                tournament.setPlayers(players);
                tournaments.add(tournament);
            }
            return tournaments;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "EmployeeDAO:viewAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public static ArrayList<Player> getTournamentPlayers(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            findPlayersStatement = dbConnection.prepareStatement(getTournamentPlayersStatementString);
            findPlayersStatement.setInt(1, id);
            rs = findPlayersStatement.executeQuery();

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
            LOGGER.log(Level.WARNING, "TournamentDAO:getTournamentPlayers " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public static int findPlayerIdByName(String name) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        int id = 0;
        try {
            findPlayersStatement = dbConnection.prepareStatement(findPlayerByNameStatementString);
            findPlayersStatement.setString(1, name);

            rs = findPlayersStatement.executeQuery();

            while (rs.next()) {
                id = rs.getInt("PlayerId");
            }
            return id;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TournamentDAO:findPlayerIdByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
        return 0;
    }

    public static void setWinner(Tournament tournament) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        int id = findPlayerIdByName(tournament.getWinner().getName());
        try {
            findPlayersStatement = dbConnection.prepareStatement(setWinnerStatementString);
            findPlayersStatement.setInt(1, id);
            findPlayersStatement.setInt(2, tournament.getId());
            findPlayersStatement.executeUpdate();
            rs = findPlayersStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TournamentDAO:setWinner " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void registerPlayer(Tournament tournament) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        try {
            findPlayersStatement = dbConnection.prepareStatement(registerPlayerStatementString);
            findPlayersStatement.setInt(1, tournament.getEmptySpots());
            findPlayersStatement.setInt(2, tournament.getId());
            findPlayersStatement.executeUpdate();
            rs = findPlayersStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TournamentDAO:registerPlayer " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void createTournament(Tournament tournament){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(createStatementString);
            insertStatement.setInt(1, tournament.getId());
            insertStatement.setString(2, tournament.getName());
            insertStatement.setString(3, tournament.getLocation());
            insertStatement.setInt(4, tournament.getYear());
            insertStatement.setInt(5, tournament.getValue());
            insertStatement.setInt(6,tournament.getRegisteredPlayers());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TournamentDAO:create " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void updateTournament(Tournament tournament){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        try {
            findPlayersStatement = dbConnection.prepareStatement(updateStatementString);
            findPlayersStatement.setInt(5, tournament.getId());
            findPlayersStatement.setString(1, tournament.getName());
            findPlayersStatement.setString(2, tournament.getLocation());
            findPlayersStatement.setInt(3, tournament.getYear());
            findPlayersStatement.setInt(4, tournament.getValue());
            findPlayersStatement.executeUpdate();
            rs = findPlayersStatement.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "TournamentDAO:updateTournament " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void deleteTournament(Tournament tournament){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, tournament.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"TournamentDAO:deleteTournament " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
