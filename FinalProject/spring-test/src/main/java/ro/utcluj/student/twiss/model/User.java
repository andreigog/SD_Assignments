
package ro.utcluj.student.twiss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String username;
    private String password;
    private int age;
    private String hobby;


    public String getId() {
        return this.id;
    }


    public void setId(
            final String id) {
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


    public int getAge() {
        return this.age;
    }


    public void setAge(
            final int age) {
        this.age = age;
    }


    public String getHobby() {
        return this.hobby;
    }


    public void setHobby(
            final String hobby) {
        this.hobby = hobby;
    }

}
