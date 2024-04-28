package com.nightstalker;

import com.nightstalker.people.Profile;
import com.nightstalker.account.Account;
import com.nightstalker.menu.Menu;
import com.nightstalker.menu.ASCIIart;
import com.nightstalker.property.Property;
import com.nightstalker.utility.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.nightstalker.utility.AccountManager.*;
public class Main {

    public static void main(String[] args) {
        // List<Optional<Account>> accounts = AccountManager.getAccount();
        List<Optional<Property>> property = SerializeManager.read("property.ser");
        List<Optional<Profile>> profile = SerializeManager.read("profile.ser");



        Menu startPageMenu = new Menu(CreateList.create("Log in", "Register", "Help", "Exit"));
        System.out.println(ASCIIart.welcome());
        Scanner scanner = new Scanner(System.in);
        boolean exitFlag = true;
        while (exitFlag){
            property = EndAuction.checkAuction(property);
            property = EndRent.checkRent(property);
            switch (startPageMenu.prompt(scanner)) {
                case 1:
                    System.out.println(ASCIIart.login());
                    Optional<Profile> user = logIn(profile);
                    if(user.isPresent()) {
                        System.out.println("Logged in successfully");
                        property = user.get().prompt(property, profile, scanner);
                    }
                    else
                        break;

                    break;
                case 2:
                    System.out.println(ASCIIart.register());
                    Account account = createAccount();
                    Profile profileCreated = ProfileManager.createProfile(account);
                    profile.add(Optional.of(profileCreated));
                    System.out.println("You have been registered Successfully");
                    break;
                case 3:
                    System.out.println(profile);
                    System.out.println(property);
                    break;
                case 4:
                    SerializeManager.write("property.ser", property);
                    SerializeManager.write("profile.ser", profile);
                    exitFlag = false;
                    break;
            }

        }
    }

}