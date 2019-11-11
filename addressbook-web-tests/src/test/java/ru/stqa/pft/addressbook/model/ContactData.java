package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id; /*= Integer.MAX_VALUE;*/
    private String firstname;
    private String lastname;
    private String middlename;
    private String group;
    private String mobile;
    private String home;
    private String work;
    private String email;
    private String email2;
    private String email3;
    private String allPhones;
    private String allEmails;
    private String address;

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
                ", mobile='" + mobile + '\'' +
                ", home='" + home + '\'' +
                ", work='" + work + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", address='" + address + '\'' +
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
    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }
    public ContactData withGroup(String group) {
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
