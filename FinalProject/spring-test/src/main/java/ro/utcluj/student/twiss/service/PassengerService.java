package ro.utcluj.student.twiss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcluj.student.twiss.model.Passenger;
import ro.utcluj.student.twiss.repository.PassengerRepository;

/**
 * @author andrei_gog on 27-May-18
 */
@Service
@Transactional
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger addPassenger( Passenger passenger){
        return this.passengerRepository.save(passenger);
    }


    public Iterable<Passenger> getAllPassengers() {
        return this.passengerRepository.findAll();
    }
}
