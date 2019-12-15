package ru.stqa.pft.mantis.model;

//import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserData extends Users {
    private int id = Integer.MAX_VALUE;
    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public UserData withId (int id){
        this.id = id;
        return this;
    }

    public UserData withUsername (String username){
        this.username = username;
        return this;
    }

    public UserData withEmail (String email){
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return /*id == userData.id &&
                Objects.equals(username, userData.username) &&*/
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UserData{" +
                /*"id=" + id +*/
                /*", username='" + username + '\'' +*/
                ", email='" + email + '\'' +
                '}';
    }
}
