
package ro.utcluj.student.twiss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.utcluj.student.twiss.model.Driver;
import ro.utcluj.student.twiss.repository.DriverRepository;


@Service
@Transactional
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	public Driver addDriver() {
		final Driver driver = new Driver();
		driver.setFirstName("Andrei");
		driver.setLastName("Gog");
		driver.setLicenseId("CV234234");
		return this.driverRepository.save(driver);
	}

	public Iterable<Driver> getAllDrivers() {
		return this.driverRepository.findAll();
	}
}
