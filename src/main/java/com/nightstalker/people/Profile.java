package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.interaction.ProfileRating;
import com.nightstalker.property.Property;

import java.io.Serializable;
import java.util.*;

/**
 * The type Profile.
 */
public class Profile implements Serializable, PromptPeople {
    private String firstName;
    private String lastName;
    private Account account;
    private List<ProfileRating> rating = new ArrayList<>();

    /**
     * Instantiates a new Profile.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param account   the account
     */
    public Profile(String firstName, String lastName, Account account) {
        this.firstName = nameFormatter(firstName);
        this.lastName = nameFormatter(lastName);
        this.account = account;
    }

    /**
     * Name formatter string.
     *
     * @param name the name
     * @return the string
     */
    public String nameFormatter(String name) {
        return name.toUpperCase().charAt(0) + name.subSequence(1, name.length()).toString().toLowerCase();
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return nameFormatter(firstName);
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = nameFormatter(firstName);
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return nameFormatter(lastName);
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = nameFormatter(lastName);
    }

    /**
     * Gets account.
     *
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets account.
     *
     * @param account the account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public List<ProfileRating> getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
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
