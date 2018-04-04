package ro.utcluj.student.dao.Model;

public class Player {
    private String name;
    private String country;
    private int id;
    private int age;
    private int gender;
    private int ranking;
    private int tournamentsWon;
    private int points;


    public Player(int id, String name, String Country, int age, int gender, int ranking) {
        this.id = id;

        this.name = name;
        this.country = Country;
        this.age = age;
        this.gender = gender;
        this.ranking = ranking;
        this.tournamentsWon = 0;
        this.points = 0;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return this.name;
    }

    public void increasePoints(int amount) {
        this.points += amount;
    }

    public int getGender() {
        return this.gender;
    }

    public String getInfo() {
        return this.name + " [" + this.ranking + "]";
    }

    public int getRanking() {
        return this.ranking;
    }
}
