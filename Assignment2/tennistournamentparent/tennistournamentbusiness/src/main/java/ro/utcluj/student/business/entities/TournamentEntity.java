package ro.utcluj.student.business.entities;

import java.util.Date;

public class TournamentEntity {

    private int id;
    private String name;
    private String location;
    private Date date;
    private int registeredPlayers;
    private int fee;


    public TournamentEntity() {
    }

    public TournamentEntity(String name, String location, Date date, int fee) {
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
        return this.name + ", " + this.location + ", " + this.fee+" $, ["+this.getRegisteredPlayers()+"/8]";
    }
}
