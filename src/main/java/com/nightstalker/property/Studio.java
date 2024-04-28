package com.nightstalker.property;

import com.nightstalker.people.Manager;

public class Studio extends Property {

    private boolean kitchen;
    private boolean balkony;

    public Studio(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, boolean kitchen, boolean balkony) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea, furniture);
        this.kitchen = kitchen;
        this.balkony = balkony;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isBalkony() {
        return balkony;
    }

    public void setBalkony(boolean balkony) {
        this.balkony = balkony;
    }

    @Override
    public String toString() {
        String title = ">>>>Type: Studio\n";
        String start = super.toString();
        String studioInformation = "\nkitchen: " + kitchen +
                                  "\nbalkony: " + balkony;
        return title.concat(start).concat(studioInformation);
    }


}
