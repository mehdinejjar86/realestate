package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.interaction.ProfileRating;
import com.nightstalker.property.Property;
import com.nightstalker.utility.ManagerHandler;

import java.util.*;

/**
 * The type Manager.
 */
public class Manager extends Profile{

    /**
     * The Rating.
     */
    List<Optional<ProfileRating>> rating = new ArrayList<>();

    /**
     * Instantiates a new Manager.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param account   the account
     */
    public Manager(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

    /**
     * Add rating.
     *
     * @param rate the rate
     */
    public void addRating(ProfileRating rate) {
        rating.add(Optional.ofNullable(rate));
    }

    /**
     * Delete rating.
     *
     * @param rate the rate
     */
    public void deleteRating(ProfileRating rate) {
        this.rating = this.rating.stream()
                .map(r -> r.orElse(null))
                .filter(r -> r != rate || r != null )
                .map(Optional::ofNullable)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager manager)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(this.getAccount(), manager.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getAccount());
    }

    @Override
    public List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        return ManagerHandler.prompt(this, property, profile, scanner);
    }
}
