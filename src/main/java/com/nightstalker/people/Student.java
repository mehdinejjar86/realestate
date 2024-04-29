package com.nightstalker.people;

import com.nightstalker.account.Account;
import com.nightstalker.menu.Menu;
import com.nightstalker.utility.CreateList;

/**
 * The type Student.
 */
public class Student extends Renter {

    /**
     * Instantiates a new Student.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param account   the account
     */
    public Student(String firstName, String lastName, Account account) {
        super(firstName, lastName, account);
    }

}
