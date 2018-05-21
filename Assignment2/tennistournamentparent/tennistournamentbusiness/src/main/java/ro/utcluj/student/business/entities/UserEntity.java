package ro.utcluj.student.business.entities;

import ro.utcluj.student.dao.model.User;

public class UserEntity {

    public enum ACCESS_RIGHT {PLAYER, ORGANIZER}
    private int id;
    private String username;
    private String password;
    private ACCESS_RIGHT accessRight;

    public UserEntity() {
    }

    public UserEntity(String username, String password, ACCESS_RIGHT accessRight) {
        this.username = username;
        this.password = password;
        this.accessRight = accessRight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ACCESS_RIGHT getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(ACCESS_RIGHT accessRight) {
        this.accessRight = accessRight;
    }


}
