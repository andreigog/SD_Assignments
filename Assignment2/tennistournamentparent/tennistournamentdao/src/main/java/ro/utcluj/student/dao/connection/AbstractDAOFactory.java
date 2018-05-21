package ro.utcluj.student.dao.connection;

import ro.utcluj.student.dao.FunctionalitiesDAO.*;

public abstract class AbstractDAOFactory {

    public enum Type {
        HIBERNATE,
        JDBC;
    }


    protected AbstractDAOFactory(){

    }

    public static AbstractDAOFactory getInstance(Type factoryType) {
        switch (factoryType) {
            case HIBERNATE:
                return new HibernateDAOFactory();
            case JDBC:
                return new JDBCDAOFactory();
            default:
                throw new IllegalArgumentException("Invalid factory");
        }
    }

    public abstract UserDAO getUserDAO();

    public abstract TournamentDAO getTournamentDAO();

    public abstract MatchDAO getMatchDAO();

    public abstract EnrollmentDAO getEnrollmentDAO();

    public abstract PlayerDAO getPlayerDAO();

    public abstract ScoreDAO getScoreDAO();
}