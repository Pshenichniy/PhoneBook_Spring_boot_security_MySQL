package com.gmail.psse69.model;

import com.gmail.psse69.validator.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "contact")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "*Enter please contact name")
    @XmlElement
    private String name;

    @Column(name = "company")
    @XmlElement
    private String company;

    @Column(name = "phone")
    @NotEmpty(message = "*Enter please contact phone")
    @Phone(message = "*Enter valid number please in format: +38(XXX)XXX-XX-XX")
    @XmlElement
    private String phone;

    @Column(name = "home_phone")
    @Phone(message = "*Enter valid number please in format:(XXX)XXX-XX-XX")
    @XmlElement
    private String homePhone;

    @Column(name = "email")
    @Email(message = "*Please enter valid email")
    @XmlElement
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User: " + getUser());
        stringBuilder.append("ID: " + getId() + " ");
        stringBuilder.append("Name: " + getName() + " ");
        stringBuilder.append("Company: " + getCompany() + " ");
        stringBuilder.append("Phone: " + getPhone() + " ");
        stringBuilder.append("Home_phone: " + getHomePhone() + " ");
        stringBuilder.append("Email: " + getEmail() + " ");

        return stringBuilder.toString();
    }
}