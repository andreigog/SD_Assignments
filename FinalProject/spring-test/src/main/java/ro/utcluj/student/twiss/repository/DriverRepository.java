package ro.utcluj.student.twiss.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.utcluj.student.twiss.model.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {

	List<Driver> findByFirstName(
		String firstName);

	List<Driver> findByLastName(
		String lastName);
}