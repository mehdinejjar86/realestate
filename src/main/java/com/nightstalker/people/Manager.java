package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.interaction.ProfileRating;
import com.nightstalker.property.Property;
import com.nightstalker.utility.ManagerHandler;

import java.util.*;

public class Manager extends Profile{

    List<Optional<ProfileRating>> rating = new ArrayList<>();

    public Manager(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

    public void addRating(ProfileRating rate) {
        rating.add(Optional.ofNullable(rate));
    }

    public void deleteRating(ProfileRating rate) {
        this.rating = this.rating.stream()
                .map(r -> r.orElse(null))
                .filter(r -> r != rate || r != null )
                .map(Optional::ofNullable)
                .toList();
    }

    @Override
    public List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        return ManagerHandler.prompt(this, property, profile, scanner);
    }
}
