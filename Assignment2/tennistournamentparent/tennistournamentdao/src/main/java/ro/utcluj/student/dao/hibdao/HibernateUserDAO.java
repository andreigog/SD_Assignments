package ro.utcluj.student.dao.hibdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ro.utcluj.student.dao.FunctionalitiesDAO.UserDAO;
import ro.utcluj.student.dao.model.User;

import java.util.List;

public class HibernateUserDAO implements UserDAO {

    public HibernateUserDAO() {
    }

    @Override
    public User getUser(String username)
    {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        System.out.println(user.getPassword());
        session.close();

        return user;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void deactivateUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
