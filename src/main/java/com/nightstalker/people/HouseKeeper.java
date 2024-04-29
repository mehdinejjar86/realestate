package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.property.Property;
import com.nightstalker.utility.HousekeeperManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The type House keeper.
 */
public class HouseKeeper extends Profile{


    /**
     * Instantiates a new House keeper.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param account   the account
     */
    public HouseKeeper(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

    @Override
    public List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        return HousekeeperManager.prompt(property, profile, this, scanner);
    }

}
