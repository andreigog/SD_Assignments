package ro.utcluj.student.business.entities;

public class PlayerEntity {

    private int id;
    private String name;
    private int age;
    private String country;
    private UserEntity user;
    private int balance;

    public PlayerEntity() {
    }

    public PlayerEntity(String name, int age, String country, UserEntity user, int balance) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.user = user;
        this.balance = balance;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString(){
        return this.name+" ["+this.country.substring(0,3)+"]";
    }

}
