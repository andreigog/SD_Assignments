package ro.utcluj.student.dao.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TournamentId")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "Date")
    private Date date;

    @Column(name = "RegisteredPlayers")
    private int registeredPlayers;

    @Column(name = "Fee")
    private int fee;

    public Tournament() {
    }

    public Tournament(String name, String location, Date date, int fee) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.fee = fee;
        this.registeredPlayers=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRegisteredPlayers() {
        return registeredPlayers;
    }

    public void setRegisteredPlayers(int registeredPlayers) {
        this.registeredPlayers = registeredPlayers;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.location + ", " + this.fee+" $";
    }
}
