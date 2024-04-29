package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.menu.Menu;
import com.nightstalker.property.Property;
import com.nightstalker.utility.CreateList;
import com.nightstalker.utility.HousekeeperManager;
import com.nightstalker.utility.RenterHandler;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The type Renter.
 */
public class Renter extends Profile{

    /**
     * Instantiates a new Renter.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param account   the account
     */
    public Renter(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

    @Override
    public List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        return RenterHandler.prompt(property, profile, this, scanner);
    }

}
