
package ro.utcluj.student.twiss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }


    public void setId(
            final Long id) {
        this.id = id;
    }


    public String getUsername() {
        return this.username;
    }


    public void setUsername(
            final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }


    public void setPassword(
            final String password) {
        this.password = password;
    }

}
