package com.nightstalker.utility;

import com.nightstalker.interaction.ProfileRating;
import com.nightstalker.interaction.PropertyRating;
import com.nightstalker.menu.Menu;
import com.nightstalker.people.Buyer;
import com.nightstalker.people.Manager;
import com.nightstalker.people.Profile;
import com.nightstalker.property.Property;
import com.nightstalker.property.PropertyStatus;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class BuyHandler {

    private static final String[] option = {"Search", "Search Filtered", "My Properties", "Log out"};
    private static final String[] decision = {"Buy", "Return"};
    public static List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, Buyer owner, Scanner scanner) {
        Menu main = new Menu(CreateList.create(option));
        boolean logoutFlag = false;
        while(!logoutFlag) {
            switch (main.prompt(scanner))
            {
                case 1:
                    if ( Period.between(owner.getAccount().getDob(), LocalDate.now()).getYears() < 18 ){
                        System.out.println("You are not 18 yet! you cannot buy a property or engage in an auction!");
                    }

                    List<Property> availableForSale = property.stream()
                            .map(p -> p.orElse(null))
                            .filter(Objects::nonNull)
                            .filter(p-> p.getStatus().equals(PropertyStatus.FOR_SALE) || p.getStatus().equals(PropertyStatus.AUCTION))
                            .toList();

                    if (availableForSale.isEmpty()) {
                        System.out.println("There is no property available!");
                        break;
                    }

                    List<Optional<String>> propertyList = new ArrayList<>(availableForSale.stream()
                            .map(p -> String.format("%s%nPrice: %s%n", p, NumberFormat.getCurrencyInstance().format(p.getPrice())))
                            .map(Optional::ofNullable)
                            .toList());
                    propertyList.add(Optional.of("Return"));
                    Menu propertyMenu = new Menu(propertyList);
                    int selectedProperty = propertyMenu.prompt(scanner);
                    if (selectedProperty == propertyList.size())
                        break;
                    Property selectedForSale = availableForSale.get(selectedProperty-1);
                    System.out.println(propertyList.get(selectedProperty-1).orElse("Something WRONG!"));

                    if (selectedForSale.getStatus().equals(PropertyStatus.FOR_SALE) ){
                        Menu decisionMenu = new Menu(CreateList.create(decision));
                        int decisionToBuy = decisionMenu.prompt(scanner);
                        if (decisionToBuy == 2)
                            break;
                        selectedForSale.setOwner(owner);
                        Manager managerReviewed = selectedForSale.getManager();
                        selectedForSale.setManager(null);
                        selectedForSale.setStatus(PropertyStatus.SOLD);
                        System.out.println("Property was bought successfully!");

                        int rateScore = 0;

                        System.out.println("Please take a moment and fill a review for how it was your purchase");
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
                        PropertyRating propertyRating = new PropertyRating(owner, rateScore, commentProperty);
                        selectedForSale.addRating(propertyRating);
                } else {
                        System.out.println("Please enter a bid");
                        System.out.print(">> ");
                        String newPrice = scanner.nextLine();
                        BigDecimal price = BigDecimal.ZERO;
                        try {
                            price = new BigDecimal(newPrice);
                            BigDecimal check = selectedForSale.getPrice().subtract(price);
                            if (check.intValue()<0) {
                                selectedForSale.setPrice(price);
                                selectedForSale.setBidder(owner);
                                System.out.println("Your bid was put successfully");
                                break;
                            }
                            System.out.println("That is an invalid price!");
                        } catch (Exception e)
                        {
                            System.out.println("That is an invalid price!");
                        }
                    }
                    break;
                case 2:
                    System.out.println("WIP");
                    break;
                case 3:
                    List<Property> ownedProperty = property.stream()
                            .map(p -> p.orElse(null))
                            .filter(Objects::nonNull)
                            .filter(p -> p.getOwner() != null)
                            .filter(p -> p.getOwner().equals(owner))
                            .toList();

                    if (ownedProperty.isEmpty()){
                        System.out.println("You do not own any property yet!");
                        break;
                    }
                    ownedProperty.forEach(System.out::println);
                    break;
                case 4:
                    logoutFlag = true;
                    break;
            }
        }
        return property;
    }
}
