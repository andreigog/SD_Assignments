
package ro.utcluj.student.twiss.dto;


public class UserDTO {
    private String username;
    private String password;

    public UserDTO() {
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
