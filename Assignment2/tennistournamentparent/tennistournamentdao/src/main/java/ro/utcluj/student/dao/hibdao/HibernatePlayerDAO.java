package ro.utcluj.student.dao.hibdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ro.utcluj.student.dao.FunctionalitiesDAO.PlayerDAO;
import ro.utcluj.student.dao.model.Player;
import ro.utcluj.student.dao.model.User;

import org.hibernate.query.Query;
import java.util.List;

public class HibernatePlayerDAO implements PlayerDAO {

    @Override
    public Player createPlayer(Player player) {
        return null;
    }

    @Override
    public void updatePlayer(Player player) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("update Player set name = :name, " +
                "                                             age = :age," +
                "                                             country = :country," +
                "                                             balance = :balance," +
                "                                             user = :user" +
                "                                              where id = :id");
        query.setParameter("name", player.getName());
        query.setParameter("country", player.getCountry());
        query.setParameter("age", player.getAge());
        query.setParameter("balance", player.getBalance());
        query.setParameter("user", player.getUser());
        query.setParameter("id", player.getId());
        query.executeUpdate();
        session.close();
    }

    @Override
    public List<Player> getAllPlayers() {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Player");
        List<Player> players = query.getResultList();
        session.close();
        return players;
    }

    @Override
    public Player getUserPlayer(User user) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Player where user = :user");
        query.setParameter("user",user);
        Player player = (Player) query.uniqueResult();
        session.close();
        return player;
    }

    @Override
    public void deletePlayer(Player player) {

    }
}
