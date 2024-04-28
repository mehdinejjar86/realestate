package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.menu.Menu;
import com.nightstalker.property.Property;
import com.nightstalker.utility.BuyHandler;
import com.nightstalker.utility.ContractorHandler;
import com.nightstalker.utility.CreateList;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Contractor extends Profile {

    public Contractor(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

    @Override
    public List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        return ContractorHandler.prompt(property, profile, this, scanner);
    }
}
