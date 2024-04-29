package com.nightstalker.utility;

import com.nightstalker.people.*;
import com.nightstalker.account.Account;
import com.nightstalker.menu.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Profile manager.
 */
public class ProfileManager {
    /**
     * The constant nameRegex.
     */
    public static String nameRegex = "[\\w]+[-]?[\\w]+$";

    /**
     * Create profile profile.
     *
     * @param account the account
     * @return the profile
     */
    public static Profile createProfile(Account account) {
        System.out.println("Please enter a valid first name");
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        while (true) {
            System.out.print(">> ");
            firstName = scanner.nextLine();
            if (checkName(firstName))
                break;
            else
                System.out.println("Invalid first name format");
        }
        System.out.println("Please enter a valid last name");
        while (true) {
            System.out.print(">> ");
            lastName = scanner.nextLine();
            if (checkName(lastName))
                break;
            else
                System.out.println("Invalid last name format");
        }
        System.out.println("Please choose a profile type");
        Menu profileType = new Menu(CreateList.create("Buyer", "Manager", "Renter", "Housekeeper", "Contractor"));



        return switch (profileType.prompt(scanner)) {
            case 1 -> new Buyer(firstName, lastName, account);
            case 2 -> new Manager(firstName, lastName, account);
            case 3 -> {
                Menu renterType = new Menu(CreateList.create("Student", "Worker"));
                System.out.println("Please select the appropriate occupancy");
                yield switch (renterType.prompt(scanner)) {
                    case 1 -> new Student(firstName, lastName, account);
                    case 2 -> new Worker(firstName, lastName, account);
                    default -> throw new IllegalStateException("Unexpected value: ");
                };
            }
            case 4 -> new HouseKeeper(firstName, lastName, account);
            case 5 -> new Contractor(firstName, lastName, account);
            default -> throw new IllegalStateException("Unexpected value: ");
        };
    }

    /**
     * Check name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean checkName(String name) {
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.find();

    }


}
