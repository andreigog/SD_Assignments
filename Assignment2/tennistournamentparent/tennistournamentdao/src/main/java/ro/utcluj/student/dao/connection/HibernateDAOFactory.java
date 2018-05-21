package ro.utcluj.student.dao.connection;

import ro.utcluj.student.dao.FunctionalitiesDAO.*;
import ro.utcluj.student.dao.hibdao.*;

public class HibernateDAOFactory extends AbstractDAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new HibernateUserDAO();
    }

    @Override
    public TournamentDAO getTournamentDAO() {
        return new HibernateTournamentDAO();
    }

    @Override
    public MatchDAO getMatchDAO() {
        return new HibernateMatchDAO();
    }

    @Override
    public EnrollmentDAO getEnrollmentDAO() {
        return new HibernateEnrollmentDAO();
    }

    @Override
    public PlayerDAO getPlayerDAO() {
        return new HibernatePlayerDAO();
    }

    @Override
    public ScoreDAO getScoreDAO() { return new HibernateScoreDAO(); }

}