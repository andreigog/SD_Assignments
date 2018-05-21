package ro.utcluj.student.dao.hibdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ro.utcluj.student.dao.FunctionalitiesDAO.MatchDAO;
import ro.utcluj.student.dao.model.Match;
import ro.utcluj.student.dao.model.Score;
import ro.utcluj.student.dao.model.Tournament;

import java.util.List;

public class HibernateMatchDAO implements MatchDAO {
    @Override
    public Match createMatch(Match match) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.save(match);
        session.close();
        return match;
    }

    @Override
    public List<Match> getAllTournamentMatches(Tournament tournament) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Match where tournament = :tournament");
        query.setParameter("tournament", tournament);
        List<Match> matches = query.list();
        session.close();
        return matches;
    }

    @Override
    public void updateMatch(Match match) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        javax.persistence.Query query = session.createQuery("update Match set winner = :winner " +
                "                                             where id = :id");
        query.setParameter("winner", match.getWinner());
        query.setParameter("id", match.getId());
        query.executeUpdate();
        session.close();
    }
}
