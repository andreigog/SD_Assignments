
package ro.utcluj.student.twiss.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Min(value = 18)
    @Max(value = 90)
    private int age;
    @NotNull
    @Size(min=8, max=8)
    private String licenseId;

    public Driver() {
    }

    public Driver(String name, int age, String licenseId) {
        this.name = name;
        this.age = age;
        this.licenseId = licenseId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(
            final Long id) {
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

    public String getLicenseId() {
        return this.licenseId;
    }

    public void setLicenseId(
            final String licenseId) {
        this.licenseId = licenseId;
    }

}
