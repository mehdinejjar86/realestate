package com.nightstalker.utility;

import com.nightstalker.account.Month;
import com.nightstalker.people.Contractor;
import com.nightstalker.people.HouseKeeper;
import com.nightstalker.people.Manager;
import com.nightstalker.menu.Menu;
import com.nightstalker.people.Profile;
import com.nightstalker.property.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerHandler {
    public static String[] propertyType = {"House", "Villa", "Apartment", "Studio", "Commercial"};

    public static List<Optional<Property>> prompt(Manager manager, List<Optional<Property>> property, List<Optional<Profile>> profile, Scanner scanner) {
        Menu menu = new Menu(CreateList.create("My Listing", "List a Property", "Log Out"));
        System.out.printf("Welcome manager %s %s!%n", manager.getFirstName(), manager.getLastName());
        boolean logoutFlag = false;
        while(!logoutFlag) {
            switch (menu.prompt(scanner)) {
                case 1: {
                    List<Property> propertyFound = property.stream()
                            .map(p -> p.orElse(null))
                            .filter(Objects::nonNull)
                            .filter(p -> p.getManager().equals(manager))
                            .toList();
                    if(propertyFound.isEmpty())
                        System.out.println("No listing yet");
                    else {
                        List<Optional<String>> listingList = new ArrayList<>(propertyFound.stream()
                                .map(Object::toString)
                                .map(Optional::ofNullable)
                                .toList());
                        listingList.add(Optional.of("Return"));
                        Menu listingMenu = new Menu(listingList);
                        int listingSelected = listingMenu.prompt(scanner);
                        if (listingSelected == listingList.size())
                            break;
                        Property selectedProperty = propertyFound.get(listingSelected-1);
                        System.out.println(selectedProperty);
                        Menu interaction = new Menu(CreateList.create("Assign Housekeeper", "Assign Contractor", "Delete Property", "Cancel"));
                        boolean cancelFlag = false;
                        while (!cancelFlag) {
                            switch (interaction.prompt(scanner)) {
                                case 1:
                                    List<Optional<Profile>> allHouseKeeper = profile.stream()
                                            .map(p-> p.orElse(null))
                                            .filter(Objects::nonNull)
                                            .filter(p -> p instanceof HouseKeeper)
                                            .map(Optional::of).toList();

                                    List<Optional<Property>> propertyStream = property;
                                    List<Optional<Profile>> availableKeeper = allHouseKeeper.stream()
                                            .map(Optional::get)
                                            .filter(k -> propertyStream.stream().map(p -> p.orElse(null))
                                                    .filter(Objects::nonNull)
                                                    .map(Property::getHousekeeper)
                                                    .map(hp -> hp.stream().map(hk -> hk.orElse(null)).filter(Objects::nonNull).toList())
                                                    .noneMatch(p -> p.contains(k)))
                                            .map(Optional::of).toList();

                                    if (availableKeeper.isEmpty()) {
                                        System.out.println("No house keeper is available!");
                                        break;
                                    }

                                    String[] keepers = availableKeeper.stream()
                                            .map(hk -> hk.orElse(null))
                                            .filter(Objects::nonNull).map(Profile::toString)
                                            .toArray(String[]::new);

                                    Menu keepersMenu = new Menu(CreateList.create(keepers));
                                    int selectedKeeper = keepersMenu.prompt(scanner);
                                    selectedProperty.addHouseKeeper((HouseKeeper) availableKeeper.get(selectedKeeper - 1).get());
                                    break;
                                case 2:
                                    List<Optional<Profile>> allContractor = profile.stream()
                                            .map(p-> p.orElse(null))
                                            .filter(Objects::nonNull)
                                            .filter(p -> p instanceof Contractor)
                                            .map(Optional::of).toList();

                                    List<Optional<Property>> contractorStream = property;
                                    List<Optional<Profile>> availableContractor = allContractor.stream()
                                            .map(Optional::get)
                                            .filter(c -> contractorStream.stream().map(p -> p.orElse(null))
                                                    .filter(Objects::nonNull)
                                                    .map(Property::getContractor)
                                                    .map(cp -> cp.stream().map(cl -> cl.orElse(null)).filter(Objects::nonNull).toList())
                                                    .noneMatch(p -> p.contains(c)))
                                            .map(Optional::of).toList();

                                    if (availableContractor.isEmpty()) {
                                        System.out.println("No contractor is available!");
                                        break;
                                    }
                                    String[] contractors = availableContractor.stream()
                                            .map(hk -> hk.orElse(null))
                                            .filter(Objects::nonNull).map(Profile::toString)
                                            .toArray(String[]::new);

                                    Menu contractorMenu = new Menu(CreateList.create(contractors));
                                    int selectedContractor = contractorMenu.prompt(scanner);
                                    selectedProperty.addContractor((Contractor) availableContractor.get(selectedContractor - 1).get());
                                    break;
                                case 3:
                                    property = new ArrayList<>(property.stream()
                                            .map(p -> p.orElse(null))
                                            .filter(Objects::nonNull)
                                            .filter(p -> !p.equals(selectedProperty))
                                            .map(Optional::ofNullable)
                                            .toList());
                                    cancelFlag = true;
                                    break;
                                case 4:
                                    cancelFlag = true;
                            }
                        }
                    }
                    break;
                }
                case 2:
                    System.out.println("Choose the type of the property");
                    Menu typePropertyMenu = new Menu(Arrays.stream(ManagerHandler.propertyType).map(Optional::of).toList());
                    Property createdProperty = switch (typePropertyMenu.prompt(scanner))
                    {
                        case 1 -> createHouse(manager, scanner);
                        case 2 -> createVilla(manager, scanner);
                        case 3 -> createApartment(manager, scanner);
                        case 4 -> createStudio(manager, scanner);
                        case 5 -> createCommercial(manager, scanner);
                        default -> throw new IllegalStateException("Unexpected value: ");
                    };
                    System.out.println("Please choose status for the property");

                    Menu menuStatus = new Menu(CreateList.create("For Sale", "For Rent", "Auction"));
                    switch (menuStatus.prompt(scanner)){
                        case 1:
                            createdProperty.setStatus(PropertyStatus.FOR_SALE);
                            BigDecimal salePrice =  computePrice("Please enter your selling price in $", scanner);
                            createdProperty.setPrice(salePrice);
                            break;
                        case 2:
                            BigDecimal rentPrice =  computePrice("Please enter your renting price per day in $", scanner);
                            createdProperty.setPrice(rentPrice);
                            createdProperty.setStatus(PropertyStatus.FOR_RENT);
                            break;
                        case 3:
                            BigDecimal price =  computePrice("Please enter your starting bidding price in $", scanner);
                            createdProperty.setPrice(price);
                            createdProperty.setStatus(PropertyStatus.AUCTION);

                            int dayI = 0;
                            int monthI = 0;
                            int yearI = 0;

                            System.out.println("Please provide the date of the end of the bid under this format: dd-mm-yyyy");

                            while(true) {
                                boolean correctDay = false;
                                boolean correctMonth = false;
                                boolean correctYear = false;
                                System.out.print(">> ");
                                String date = scanner.nextLine();
                                Pattern datePattern = Pattern.compile(AccountManager.dateRegex, Pattern.COMMENTS);
                                Matcher dateMatcher = datePattern.matcher(date);

                                if (dateMatcher.find()) {
                                    String day = dateMatcher.group(1);
                                    String month = dateMatcher.group(2);
                                    String year = dateMatcher.group(3);

                                    try {
                                        monthI = Integer.parseInt(month);
                                        if (monthI > 0 && monthI <= 12)
                                            correctMonth = true;
                                        else {
                                            System.out.printf("Invalid month of bid: %d%n", monthI);
                                            continue;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.printf("Invalid month of bid: %s%n", e.getMessage());
                                        continue;
                                    }

                                    try {
                                        yearI = Integer.parseInt(year);
                                        int currentYear = Year.now().getValue();
                                        if (yearI >= currentYear)
                                            correctYear = true;
                                        else {
                                            System.out.printf("Invalid year of bid: %d%n", yearI);
                                            continue;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.printf("Invalid year of bid: %s%n", e.getMessage());
                                        continue;
                                    }

                                    try {
                                        Month monthE = Month.values()[monthI - 1];
                                        dayI = Integer.parseInt(day);
                                        if (dayI > 0 && dayI <= monthE.getDay(yearI)) {
                                            correctDay = true;
                                        }
                                        else {
                                            System.out.printf("Invalid day of bid: %d%n", dayI);
                                            continue;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.printf("Invalid day of bid: %s%n", e.getMessage());
                                        continue;
                                    }
                                } else {
                                    System.out.println("Invalid date format! Correct format is dd-mm-yyyy");
                                }

                                if (correctDay && correctMonth && correctYear && LocalDate.of(yearI, monthI, dayI).isAfter(createdProperty.getDateOfListing()))
                                    break;
                            }

                            LocalDate endDateBid = LocalDate.of(yearI, monthI, dayI);
                            createdProperty.setEndAvailability(endDateBid);

                            break;
                    }

                    property.add(Optional.ofNullable(createdProperty));
                    break;
                case 3:
                    logoutFlag = true;
                    break;
            }
        }
        return property;
    }

    private static BigDecimal computePrice(String message, Scanner scanner) {
        System.out.println(message);
        BigDecimal price = BigDecimal.ZERO;
        while (true)
        {
            try {
                price = new BigDecimal(scanner.nextLine());
                if(price.doubleValue() > 0)
                    break;
                else
                    System.out.println("Invalid value");
            } catch (Exception e)
            {
                System.out.println("Invalid value");
            }
        }
        return price;
    }

    private static boolean isFurnished(Scanner scanner) {
        boolean furniture = true;
        System.out.println("The property is furnished [y]/n");
        while (true) {
            String furnished = scanner.nextLine();
            if (("y".compareTo(furnished.toLowerCase()) == 0) || furnished.isEmpty()) {
                furniture = true;
                break;
            }
            else if("n".compareTo(furnished.toLowerCase()) == 0)
                break;
            else
                System.out.println("Invalid choice");
        }
        return furniture;
    }

    private static double floorAreaM2(Scanner scanner) {
        double floorArea = 0.0;
        System.out.println("Enter the area of the property in meter squared");
        while (true) {
            System.out.print(">> ");
            String floorAreaS = scanner.nextLine();

            try {
                floorArea = Integer.parseInt(floorAreaS);
                if (floorArea >= 2 )
                    break;
                else {
                    System.out.printf("Invalid value for area: %f%n", floorArea);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for area: %s%n", e.getMessage());
            }
        }
        return floorArea;
    }

    private static int bathroomCount(Scanner scanner) {
        int bathroom = 0;
        System.out.println("Enter number of bathroom");
        while (true) {
            System.out.print(">> ");
            String bathroomS = scanner.nextLine();

            try {
                bathroom = Integer.parseInt(bathroomS);
                if (bathroom > 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of bathroom: %d%n", bathroom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of bathroom: %s%n", e.getMessage());
            }
        }

        return bathroom;
    }


    private static String streetAddress(String msg, Scanner scanner) {
        System.out.println(msg);
        String stAddress;
        while (true) {
            System.out.print(">> ");
            stAddress = scanner.nextLine();
            if (AddressManager.validateAddress(stAddress))
                break;
            else
                System.out.println("Invalid address format");
        }
        return stAddress.toLowerCase();
    }

    private static House createHouse(Manager manager, Scanner scanner) {
        String stAddress = streetAddress("Enter the Street Address", scanner);
        String lnAddress = streetAddress("Enter the Line Address", scanner);
        int bathroom = bathroomCount(scanner);
        double floorArea = floorAreaM2(scanner);
        boolean furniture = isFurnished(scanner);

        int kitchen = 0;
        int garage = 0;
        int livingRoom = 0;
        int room = 1;
        int floor = 1;
        int porch = 0;
        boolean basement = false;

        System.out.println("Enter number of kitchen [If there is none input 0]");
        while (true) {
            System.out.print(">> ");
            String kitchenI = scanner.nextLine();

            try {
                kitchen = Integer.parseInt(kitchenI);
                if (kitchen >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of kitchen: %d%n", kitchen);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of kitchen: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of garage [If there is none input 0]");
        while (true) {
            System.out.print(">> ");
            String garageI = scanner.nextLine();

            try {
                garage = Integer.parseInt(garageI);
                if (garage >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of garage: %d%n", garage);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of garage: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of living rooms [If there is none input 0]");
        while (true) {
            System.out.print(">> ");
            String livingRoomI = scanner.nextLine();

            try {
                livingRoom = Integer.parseInt(livingRoomI);
                if (livingRoom >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of living rooms: %d%n", livingRoom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of garage: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of room");
        while (true) {
            System.out.print(">> ");
            String roomI = scanner.nextLine();

            try {
                room = Integer.parseInt(roomI);
                if (room > 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of room: %d%n", livingRoom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of room: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of floor");
        while (true) {
            System.out.print(">> ");
            String floorI = scanner.nextLine();

            try {
                floor = Integer.parseInt(floorI);
                if (floor > 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of floor: %d%n", livingRoom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of floor: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of porch [If there is none input 0]");
        while (true) {
            System.out.print(">> ");
            String porchI = scanner.nextLine();

            try {
                porch = Integer.parseInt(porchI);
                if (porch >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of floor: %d%n", livingRoom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of floor: %s%n", e.getMessage());
            }
        }

        System.out.println("The property has a basement [y]/n");
        while (true) {
            String basementI = scanner.nextLine();
            if (("y".compareTo(basementI.toLowerCase()) == 0) || basementI.isEmpty()) {
                basement = true;
                break;
            }
            else if("n".compareTo(basementI.toLowerCase()) == 0)
                break;
            else
                System.out.println("Invalid choice");
        }
        return new House(manager, stAddress, lnAddress, room, bathroom, floorArea, furniture, kitchen, garage, livingRoom, floor, porch);
    }

    public static Villa createVilla(Manager manager, Scanner scanner) {
        String stAddress = streetAddress("Enter the Street Address", scanner);
        String lnAddress = streetAddress("Enter the Line Address", scanner);
        int bathroom = bathroomCount(scanner);
        double floorArea = floorAreaM2(scanner);
        boolean furniture = isFurnished(scanner);

        int kitchen = 0;
        int garage = 0;
        int livingRoom = 0;
        int room = 1;
        int floor = 1;
        double outdoorArea = 0;
        boolean basement = false;

        System.out.println("Enter number of kitchen");
        while (true) {
            System.out.print(">> ");
            String kitchenI = scanner.nextLine();

            try {
                kitchen = Integer.parseInt(kitchenI);
                if (kitchen >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of kitchen: %d%n", kitchen);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of kitchen: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of garage [If there is none, input 0]");
        while (true) {
            System.out.print(">> ");
            String garageI = scanner.nextLine();

            try {
                garage = Integer.parseInt(garageI);
                if (garage >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of garage: %d%n", garage);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of garage: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of living rooms [If there is none, input 0]");
        while (true) {
            System.out.print(">> ");
            String livingRoomI = scanner.nextLine();

            try {
                livingRoom = Integer.parseInt(livingRoomI);
                if (livingRoom >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of living rooms: %d%n", livingRoom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of garage: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of room");
        while (true) {
            System.out.print(">> ");
            String roomI = scanner.nextLine();

            try {
                room = Integer.parseInt(roomI);
                if (room > 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of room: %d%n", room);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of room: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter number of floor");
        while (true) {
            System.out.print(">> ");
            String floorI = scanner.nextLine();

            try {
                floor = Integer.parseInt(floorI);
                if (floor > 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of floor: %d%n", floor);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of floor: %s%n", e.getMessage());
            }
        }

        System.out.println("Enter the outdoor area");
        while (true) {
            System.out.print(">> ");
            String outdoorAreaI = scanner.nextLine();

            try {
                outdoorArea = Double.parseDouble(outdoorAreaI);
                if (outdoorArea > 2)
                    break;
                else {
                    System.out.printf("Invalid value for humber of outdoor area: %d%n", livingRoom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of outdoor area: %s%n", e.getMessage());
            }
        }

        System.out.println("The property has a basement [y]/n");
        while (true) {
            String basementI = scanner.nextLine();
            if (("y".compareTo(basementI.toLowerCase()) == 0) || basementI.isEmpty()) {
                basement = true;
                break;
            }
            else if("n".compareTo(basementI.toLowerCase()) == 0)
                break;
            else
                System.out.println("Invalid choice");
        }
        return new Villa(manager, stAddress, lnAddress, room, bathroom, floorArea, furniture, kitchen, garage, livingRoom, floor, outdoorArea, basement);
    }

    public static Apartment createApartment(Manager manager, Scanner scanner) {
        String stAddress = streetAddress("Enter the Street Address", scanner);
        String lnAddress = streetAddress("Enter the Line Address", scanner);
        int bathroom = bathroomCount(scanner);
        double floorArea = floorAreaM2(scanner);
        boolean furniture = isFurnished(scanner);

        int kitchen = 0;
        boolean garage = false;
        int livingRoom = 0;
        int room = 1;

        System.out.println("Enter number of kitchen");
        while (true) {
            System.out.print(">> ");
            String kitchenI = scanner.nextLine();

            try {
                kitchen = Integer.parseInt(kitchenI);
                if (kitchen > 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of kitchen: %d%n", kitchen);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of kitchen: %s%n", e.getMessage());
            }
        }

        System.out.println("The property has a garage [y]/n");
        while (true) {
            String garageI = scanner.nextLine();
            if (("y".compareTo(garageI.toLowerCase()) == 0) || garageI.isEmpty()) {
                garage = true;
                break;
            }
            else if("n".compareTo(garageI.toLowerCase()) == 0)
                break;
            else
                System.out.println("Invalid choice");
        }


        System.out.println("Enter number of living rooms [If there is none, input 0]");
        while (true) {
            System.out.print(">> ");
            String livingRoomI = scanner.nextLine();

            try {
                livingRoom = Integer.parseInt(livingRoomI);
                if (livingRoom >= 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of living rooms: %d%n", livingRoom);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of garage: %s%n", e.getMessage());
            }
        }


        return new Apartment(manager, stAddress, lnAddress, room, bathroom, floorArea, furniture, garage, kitchen, livingRoom);
    }

    public static Studio createStudio(Manager manager, Scanner scanner) {
        String stAddress = streetAddress("Enter the Street Address", scanner);
        String lnAddress = streetAddress("Enter the Line Address", scanner);
        int room = 1;
        int bathroom = bathroomCount(scanner);
        double floorArea = floorAreaM2(scanner);
        boolean furniture = isFurnished(scanner);

        boolean kitchen = false;
        boolean balkony = false;

        System.out.println("The property has a kitchen [y]/n");
        while (true) {
            String kitchenI = scanner.nextLine();
            if (("y".compareTo(kitchenI.toLowerCase()) == 0) || kitchenI.isEmpty()) {
                kitchen = true;
                break;
            }
            else if("n".compareTo(kitchenI.toLowerCase()) == 0)
                break;
            else
                System.out.println("Invalid choice");
        }

        System.out.println("The property has a balkony [y]/n");
        while (true) {
            String balkonyI = scanner.nextLine();
            if (("y".compareTo(balkonyI.toLowerCase()) == 0) || balkonyI.isEmpty()) {
                balkony = true;
                break;
            }
            else if("n".compareTo(balkonyI.toLowerCase()) == 0)
                break;
            else
                System.out.println("Invalid choice");
        }

        return new Studio(manager, stAddress, lnAddress, room, bathroom, floorArea, furniture, kitchen, balkony);
    }

    public static Commercial createCommercial(Manager manager, Scanner scanner) {
        String stAddress = streetAddress("Enter the Street Address", scanner);
        String lnAddress = streetAddress("Enter the Line Address", scanner);
        int room = 1;
        int bathroom = bathroomCount(scanner);
        double floorArea = floorAreaM2(scanner);
        boolean furniture = isFurnished(scanner);
        int floor = 1;

        System.out.println("Enter number of floor");
        while (true) {
            System.out.print(">> ");
            String floorI = scanner.nextLine();

            try {
                floor = Integer.parseInt(floorI);
                if (floor > 0)
                    break;
                else {
                    System.out.printf("Invalid value for humber of floor: %d%n", floor);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid value for humber of floor: %s%n", e.getMessage());
            }
        }

        return new Commercial(manager, stAddress, lnAddress, room, bathroom, floorArea, furniture, floor);
    }


}
