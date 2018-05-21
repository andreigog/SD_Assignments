package ro.utcluj.student.dao.hibdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ro.utcluj.student.dao.FunctionalitiesDAO.TournamentDAO;
import ro.utcluj.student.dao.model.Player;
import ro.utcluj.student.dao.model.Tournament;

import javax.persistence.Query;
import java.util.List;

public class HibernateTournamentDAO implements TournamentDAO {
    @Override
    public Tournament createTournament(Tournament tournament) {
        return null;
    }

    @Override
    public void updateTournament(Tournament tournament) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("update Tournament set name = :name, " +
                "                                             registeredPlayers = :registeredPlayers" +
                "                                             where id = :id");
        query.setParameter("name", tournament.getName());
        query.setParameter("registeredPlayers", tournament.getRegisteredPlayers());
        query.setParameter("id", tournament.getId());
        query.executeUpdate();
        session.close();
    }

    @Override
    public Tournament getTournament(String name) {
        return null;
    }

    @Override
    public List<Tournament> getAllTournaments() {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Tournament");
        List<Tournament> tournaments = query.getResultList();
        session.close();
        return tournaments;
    }

    @Override
    public List<Tournament> getAllTournaments(String name, String category) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Query query;
        if (category.equals("Free")) {
            query = session.createQuery("from Tournament where name LIKE :name and fee = :fee");
            query.setParameter("name", "%" + name + "%");
            query.setParameter("fee", 0);
        } else if (category.equals("Paid")) {
            query = session.createQuery("from Tournament where name LIKE :name and fee > :fee");
            query.setParameter("name", "%" + name + "%");
            query.setParameter("fee", 0);
        } else {
            query = session.createQuery("from Tournament where name LIKE :name");
            query.setParameter("name", "%" + name + "%");
        }

        List<Tournament> tournaments = query.getResultList();
        session.close();
        return tournaments;
    }

    @Override
    public void deleteTournament(Tournament tournament) {

    }
}
