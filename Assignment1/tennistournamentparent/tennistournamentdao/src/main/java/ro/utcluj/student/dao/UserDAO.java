package ro.utcluj.student.dao;


import ro.utcluj.student.dao.Model.Player;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAO {

    protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private final static String checkUsernameStatementString = "SELECT * FROM User";
    private final static String getPlayerStatementString = "SELECT * FROM Players INNER JOIN User on User.UserId" +
            "=Players.UserId WHERE User.UserId=?";
    private final static String getUsernameStatementString = "SELECT Username FROM User WHERE UserId=?";
    private final static String getPasswordStatementString = "SELECT Password FROM User WHERE UserId=?";


    public static Integer checkUsername(String username, String password, String userType) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(checkUsernameStatementString);
            rs = findStatement.executeQuery();
            while (rs.next()) {
                if (rs.getString("Username").equals(username) && rs.getString("Password").equals(password)
                        && rs.getString("Type").equals(userType))
                    return new Integer(rs.getInt("UserId"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:checkUsername " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public static Player getPlayer(Integer userId) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        Player player = null;
        try {
            findPlayersStatement = dbConnection.prepareStatement(getPlayerStatementString);
            findPlayersStatement.setInt(1, userId.intValue());
            rs = findPlayersStatement.executeQuery();

            while (rs.next()) {
                player = new Player(rs.getInt("PlayerId"),
                        rs.getString("Name"),
                        rs.getString("Country"),
                        rs.getInt("Age"),
                        rs.getInt("Gender"),
                        rs.getInt("Ranking"));
            }
            return player;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:getPlayer " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public static String getUsername(Integer userId) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        try {
            findPlayersStatement = dbConnection.prepareStatement(getUsernameStatementString);
            findPlayersStatement.setInt(1, userId.intValue());
            rs = findPlayersStatement.executeQuery();
            rs.next();
            return rs.getString("Username");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:getUsername " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public static String getPassword(Integer userId) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPlayersStatement = null;
        ResultSet rs = null;
        try {
            findPlayersStatement = dbConnection.prepareStatement(getPasswordStatementString);
            findPlayersStatement.setInt(1, userId.intValue());
            rs = findPlayersStatement.executeQuery();
            rs.next();
            return rs.getString("Password");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:getPassword " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findPlayersStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

}
