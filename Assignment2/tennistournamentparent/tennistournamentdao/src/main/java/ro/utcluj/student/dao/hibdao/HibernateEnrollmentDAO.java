package ro.utcluj.student.dao.hibdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ro.utcluj.student.dao.FunctionalitiesDAO.EnrollmentDAO;
import ro.utcluj.student.dao.model.Enrollment;
import ro.utcluj.student.dao.model.Player;
import ro.utcluj.student.dao.model.Tournament;

import java.util.List;


public class HibernateEnrollmentDAO implements EnrollmentDAO {

    @Override
    public Enrollment getEnrollment(Player player, Tournament tournament) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Enrollment where player = :player and tournament = :tournament");
        query.setParameter("player", player);
        query.setParameter("tournament", tournament);
        Enrollment enrollment = (Enrollment) query.uniqueResult();
        session.close();
        return enrollment;
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        session.save(enrollment);
        session.close();
        return enrollment;
    }

    @Override
    public List<Enrollment> getAllTournamentEnrollMents(Tournament tournament) {
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Enrollment where tournament = :tournament");
        query.setParameter("tournament", tournament);
        List<Enrollment> enrollments = query.list();
        session.close();
        return enrollments;
    }

}
