package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id; /*= Integer.MAX_VALUE;*/

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Transient
    private String middlename;

    @Transient
    private String group;

    @Column(name="mobile")
    @Type(type="text")
    private String mobile;

    @Column(name="home")
    @Type(type="text")
    private String home;

    @Column(name="work")
    @Type(type="text")
    private String work;
    @Transient
    private String email;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String address;

    /*@Transient
    @Column(name="photo")
    @Type(type="text")
    private String photo;*/

    /*public File getPhoto() {
        return new File(photo);
    }*/

    /*public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }*/



    public String getAddress() {
        return address;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", group='" + group + '\'' +
                ", mobilePhone='" + mobile + '\'' +
                ", homePhone='" + home + '\'' +
                ", workPhone='" + work + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", address='" + address + '\'' +
                /*", photo='" + photo + '\'' +*/
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(middlename, that.middlename) &&
                Objects.equals(group, that.group) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(home, that.home) &&
                Objects.equals(work, that.work) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(address, that.address);
        /*Objects.equals(photo, that.photo);*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, middlename, group, mobile, home, work, email, email2, email3, address);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }
    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }
    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }
    public ru.stqa.pft.addressbook.model.ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public int getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getMiddlename() {
        return middlename;
    }
    public String getMobile() {
        return mobile;
    }
    public String getGroup() {
        return group;
    }
    public String getHome() {
        return home;
    }
    public String getWork() {
        return work;
    }
}
