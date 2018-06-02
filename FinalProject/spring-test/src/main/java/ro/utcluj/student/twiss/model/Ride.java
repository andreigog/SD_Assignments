package ro.utcluj.student.twiss.model;

import javax.persistence.*;

/**
 * @author andrei_gog on 27-May-18
 */
@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long driverId;
    private Long passengerId;

}
