package ro.utcluj.student.dao.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    public enum ACCESS_RIGHT {PLAYER, ORGANIZER}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Type", columnDefinition = "int")
    private ACCESS_RIGHT accessRight;

    public User() {
    }

    public User(String username, String password, ACCESS_RIGHT accessRight) {
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
