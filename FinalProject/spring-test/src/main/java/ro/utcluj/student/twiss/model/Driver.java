
package ro.utcluj.student.twiss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String licenseId;

    public Long getId() {
        return this.id;
    }

    public void setId(
            final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(
            final String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return this.lastName;
    }


    public void setLastName(
            final String lastName) {
        this.lastName = lastName;
    }


    public String getLicenseId() {
        return this.licenseId;
    }

    public void setLicenseId(
            final String licenseId) {
        this.licenseId = licenseId;
    }

}
