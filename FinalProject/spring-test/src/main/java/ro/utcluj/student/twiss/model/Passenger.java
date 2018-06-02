package ro.utcluj.student.twiss.model;

import javax.persistence.*;

/**
 * @author andrei_gog on 25-May-18
 */
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PassenerId")
    private Long id;
    private String name;
    private String location;

    public Passenger() {
    }

    public Passenger(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
