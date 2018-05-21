package ro.utcluj.student.dao.hibdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ro.utcluj.student.dao.FunctionalitiesDAO.ScoreDAO;
import ro.utcluj.student.dao.model.Match;
import ro.utcluj.student.dao.model.Score;

public class HibernateScoreDAO implements ScoreDAO {
    @Override
    public Score createScore(Score score) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.save(score);
        session.close();
        return score;
    }

    @Override
    public void updateScore(Score score) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        javax.persistence.Query query = session.createQuery("update Score set set1Player1 = :set1Player1, " +
                "set1Player2 = :set1Player2, " +
                "set2Player1 = :set2Player1, " +
                "set2Player2 = :set2Player2, " +
                "set3Player1 = :set3Player1, " +
                "set3Player2 = :set3Player2, " +
                "gamePlayer1 = :gamePlayer1, " +
                "gamePlayer2 = :gamePlayer2, " +
                "advantage = :advantage " +
                "where id = :id");
        query.setParameter("set1Player1",score.getSet1Player1());
        query.setParameter("set1Player2",score.getSet1Player2());
        query.setParameter("set2Player1",score.getSet2Player1());
        query.setParameter("set2Player2",score.getSet2Player2());
        query.setParameter("set3Player1",score.getSet3Player1());
        query.setParameter("set3Player2",score.getSet3Player2());
        query.setParameter("gamePlayer1",score.getGamePlayer1());
        query.setParameter("gamePlayer2",score.getGamePlayer2());
        query.setParameter("advantage",score.getAdvantage());
        query.setParameter("id", score.getId());
        query.executeUpdate();
        session.close();
    }
}
