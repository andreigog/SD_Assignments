package ro.utcluj.student.dao.FunctionalitiesDAO;

import ro.utcluj.student.dao.model.Enrollment;
import ro.utcluj.student.dao.model.Player;
import ro.utcluj.student.dao.model.Tournament;

import java.util.List;

public interface EnrollmentDAO {

    Enrollment getEnrollment(Player player, Tournament tournament);

    Enrollment createEnrollment(Enrollment enrollment);

    List<Enrollment> getAllTournamentEnrollMents(Tournament tournament);

}