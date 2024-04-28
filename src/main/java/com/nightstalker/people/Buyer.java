package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.menu.Menu;
import com.nightstalker.property.Property;
import com.nightstalker.utility.BuyHandler;
import com.nightstalker.utility.CreateList;
import com.nightstalker.utility.ManagerHandler;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Buyer extends Profile {

    public Buyer(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

    @Override
    public List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        return BuyHandler.prompt(property, profile, this, scanner);
    }
}
