package ro.utcluj.student.dao.Model;

import ro.utcluj.student.dao.Model.Match;
import ro.utcluj.student.dao.Model.Player;

import java.util.ArrayList;

public class Tournament {

    private int id;
    private String name;
    private int value;
    private String location;
    private ArrayList<Player> players;
    private ArrayList<Match> matches;
    private int registeredPlayers;
    private int year;
    private Player winner;

    public Tournament(int id, String name, int value, String location, int year, int registeredPlayers) {
        this.id = id;

        this.name = name;
        this.value = value;
        this.location = location;
        this.registeredPlayers = registeredPlayers;
        this.year = year;
        this.winner = null;
        this.matches = null;

    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public int getId() {
        return id;
    }

    public void registerPlayer() {
        this.registeredPlayers++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRegisteredPlayers() {
        return registeredPlayers;
    }

    public void setRegisteredPlayers(int registeredPlayers) {
        this.registeredPlayers = registeredPlayers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmptySpots() {
        return this.registeredPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getInfo() {
        return this.name + ", " + this.location + ", " + this.year;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
