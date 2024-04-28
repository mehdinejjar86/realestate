package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.interaction.ProfileRating;
import com.nightstalker.property.Property;

import java.io.Serializable;
import java.util.*;

public class Profile implements Serializable, PromptPeople {
    private String firstName;
    private String lastName;
    private Account account;
    private List<ProfileRating> rating = new ArrayList<>();

    public Profile(String firstName, String lastName, Account account) {
        this.firstName = nameFormatter(firstName);
        this.lastName = nameFormatter(lastName);
        this.account = account;
    }

    public String nameFormatter(String name) {
        return name.toUpperCase().charAt(0) + name.subSequence(1, name.length()).toString().toLowerCase();
    }

    public String getFirstName() {
        return nameFormatter(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = nameFormatter(firstName);
    }

    public String getLastName() {
        return nameFormatter(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = nameFormatter(lastName);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<ProfileRating> getRating() {
        return rating;
    }

    public void setRating(List<ProfileRating> rating) {
        this.rating = rating;
    }



    public List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        return property;
    };



    @Override
    public String toString() {
        return "Profile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return Objects.equals(firstName, profile.firstName) && Objects.equals(lastName, profile.lastName) && Objects.equals(account, profile.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, account);
    }




}
