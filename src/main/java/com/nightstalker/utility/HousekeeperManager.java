package com.nightstalker.utility;

import com.nightstalker.menu.Menu;
import com.nightstalker.people.Contractor;
import com.nightstalker.people.HouseKeeper;
import com.nightstalker.people.Profile;
import com.nightstalker.property.Property;

import java.util.*;

/**
 * The type Housekeeper manager.
 */
public class HousekeeperManager {
    private static final String[] option = {"Check Assigned Task", "Log out"};

    /**
     * Prompt list.
     *
     * @param property    the property
     * @param profile     the profile
     * @param houseKeeper the house keeper
     * @param scanner     the scanner
     * @return the list
     */
    public static List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile, HouseKeeper houseKeeper, Scanner scanner) {
        Menu main = new Menu(CreateList.create(option));
        boolean logoutFlag = false;
        while (!logoutFlag) {
            switch (main.prompt(scanner)) {
                case 1:
                    List<Optional<Property>> taskFound = property.stream()
                            .map(p -> p.orElse(null))
                            .filter(Objects::nonNull)
                            .filter(p -> p.getHousekeeper()
                                    .stream()
                                    .map(c -> c.orElse(null))
                                    .filter(Objects::nonNull)
                                    .toList()
                                    .contains(houseKeeper)).map(Optional::ofNullable).toList();

                    if (taskFound.isEmpty()) {
                        System.out.println("No task is assigned");
                        break;
                    }

                    List<Optional<String>> taskList = new ArrayList<>(taskFound.stream()
                            .map(Object::toString)
                            .map(Optional::ofNullable)
                            .toList());
                    taskList.add(Optional.of("Return"));
                    Menu taskMenu = new Menu(taskList);

                    int listingSelected = taskMenu.prompt(scanner);
                    if (listingSelected == taskList.size())
                        break;

                    Property selectedForSale = taskFound.get(listingSelected - 1).get();
                    selectedForSale.setHousekeeper(selectedForSale.getHousekeeper()
                            .stream()
                            .map(c -> c.orElse(null))
                            .filter(Objects::nonNull)
                            .filter(c -> c != houseKeeper)
                            .map(Optional::ofNullable)
                            .toList());
                    System.out.println("Task Completed!");
                    break;
                case 2:
                    logoutFlag = true;
                    break;
            }
        }
        return property;
    }
}
