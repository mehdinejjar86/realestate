package com.nightstalker.utility;

import com.nightstalker.interaction.ProfileRating;
import com.nightstalker.interaction.PropertyRating;
import com.nightstalker.menu.Menu;
import com.nightstalker.people.Manager;
import com.nightstalker.people.Profile;
import com.nightstalker.people.Renter;
import com.nightstalker.people.Student;
import com.nightstalker.property.Property;
import com.nightstalker.property.PropertyStatus;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class RenterHandler {
    private static final String[] option = {"Search", "Search Filtered", "My Rents", "Log out"};
    private static final String[] decision = {"Rent", "Return"};
    public static List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Renter renter, Scanner scanner) {
        Menu optionMenu = new Menu(CreateList.create(option));

        boolean logoutFlag = false;
        while(!logoutFlag) {
            switch (optionMenu.prompt(scanner))
            {
                case 1:
                    List<Property> availableForRent = property.stream()
                            .map(p -> p.orElse(null))
                            .filter(Objects::nonNull)
                            .filter(p-> p.getStatus().equals(PropertyStatus.FOR_RENT))
                            .filter(Property::isAvailable)
                            .toList();

                    if (availableForRent.isEmpty()) {
                        System.out.println("There is no property available!");
                        break;
                    }

                    List<Optional<String>> propertyList = new ArrayList<>(availableForRent.stream()
                            .map(p -> String.format("%s%nPrice: %s%n", p, NumberFormat.getCurrencyInstance().format(p.getPrice())))
                            .map(Optional::ofNullable)
                            .toList());
                    propertyList.add(Optional.of("Return"));
                    Menu propertyMenu = new Menu(propertyList);
                    int selectedProperty = propertyMenu.prompt(scanner);
                    if (selectedProperty == propertyList.size())
                        break;

                    Property selectedForRent = availableForRent.get(selectedProperty-1);
                    System.out.println(propertyList.get(selectedProperty-1).orElse("Something WRONG!"));
                    if (renter instanceof Student) {
                        BigDecimal studentDiscount = selectedForRent.getPrice().multiply(new BigDecimal("0.8"));
                        System.out.printf("Student discount 20 percent: %s%n", NumberFormat.getCurrencyInstance().format(studentDiscount));
                    };


                    Menu decisionMenu = new Menu(CreateList.create(decision));
                    int decisionToRent = decisionMenu.prompt(scanner);
                    if (decisionToRent == 2)
                        break;
                    System.out.println();

                    System.out.println("For how long you want to rent the property");
                    while (true) {
                        System.out.print(">> ");
                        try {
                            int day = Integer.parseInt(scanner.nextLine());
                            if(day > 0) {
                                selectedForRent.setEndAvailability(LocalDate.now().plusDays(day));
                                break;
                            }
                            System.out.println("Invalid days!");

                        } catch (NumberFormatException e) {
                            System.out.printf("Invalid day number: %s%n", e.getMessage());
                        }
                    }

                    selectedForRent.setRenter(renter);
                    Manager managerReviewed = selectedForRent.getManager();
                    selectedForRent.setAvailable(false);

                    System.out.println("Property was rented successfully!");

                    int rateScore = 0;

                    System.out.println("Please take a moment and fill a review for how it was your rent");
                    while (true) {
                        System.out.println("How satisfied were you with the dealer [1-5]");
                        System.out.print(">> ");
                        String rating = scanner.nextLine();

                        try {
                            rateScore = Integer.parseInt(rating);
                            if (rateScore > 0 && rateScore <= 5)
                                break;
                            System.out.printf("Invalid choice %d%n", rateScore);
                        } catch (NumberFormatException e) {
                            System.out.printf("Invalid value for rating: %s%n", e.getMessage());
                        }
                    }
                    System.out.println("Do you want to tell us about your experience? [To skip, just hit return]");
                    System.out.print(">> ");
                    String commentManager = scanner.nextLine();

                    ProfileRating managerRating = new ProfileRating(managerReviewed, rateScore, commentManager);
                    managerReviewed.addRating(managerRating);

                    while (true) {
                        System.out.println("How satisfied were you with the property [1-5]");
                        System.out.print(">> ");
                        String rating = scanner.nextLine();
                        try {
                            rateScore = Integer.parseInt(rating);
                            if (rateScore > 0 && rateScore <= 5)
                                break;
                            System.out.printf("Invalid choice %d%n", rateScore);
                        } catch (NumberFormatException e) {
                            System.out.printf("Invalid value for rating: %s%n", e.getMessage());
                        }
                    }

                    System.out.println("Do you want to tell us about your experience? [To skip, just hit return]");
                    System.out.print(">> ");
                    String commentProperty = scanner.nextLine();
                    PropertyRating propertyRating = new PropertyRating(renter, rateScore, commentProperty);
                    selectedForRent.addRating(propertyRating);
                    break;
                case 2:
                    System.out.println("WIP");
                    break;
                case 3:
                    List<Property> rentedProperty = property.stream()
                            .map(p -> p.orElse(null))
                            .filter(Objects::nonNull)
                            .filter(p -> p.getRenter() != null)
                            .filter(p -> p.getRenter().equals(renter))
                            .toList();

                    if (rentedProperty.isEmpty()){
                        System.out.println("You have not rented any property yet!");
                        break;
                    }
                    rentedProperty.forEach(System.out::println);
                    break;
                case 4:
                    logoutFlag = true;
                    break;
            }
        }

        return property;
    }
}
