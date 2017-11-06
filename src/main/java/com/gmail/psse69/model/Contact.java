package com.gmail.psse69.model;

import com.gmail.psse69.validator.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "*Enter please contact name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "phone")
    @NotEmpty(message = "*Enter please contact phone")
    @Phone(message = "*Enter valid number please in format: +38(0**)1234567")
    private String phone;

    @Column(name = "homephone")
    @Phone(message = "*Enter valid number please in format:(0**)123-45-67")
    private String homePhone;

    @Column(name = "email")
    @Email(message = "*Please enter valid email")
    private String email;


    @ManyToOne
    @JoinColumn(name = "useid", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
