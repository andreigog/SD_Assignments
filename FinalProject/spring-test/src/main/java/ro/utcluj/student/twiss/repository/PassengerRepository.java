package ro.utcluj.student.twiss.repository;

import org.springframework.data.repository.CrudRepository;
import ro.utcluj.student.twiss.model.Passenger;

/**
 * @author andrei_gog on 27-May-18
 */
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
}
