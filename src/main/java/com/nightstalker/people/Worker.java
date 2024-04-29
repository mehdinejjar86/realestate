package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.menu.Menu;
import com.nightstalker.utility.CreateList;

/**
 * The type Worker.
 */
public class Worker extends Renter {

    /**
     * Instantiates a new Worker.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param account   the account
     */
    public Worker(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

}
