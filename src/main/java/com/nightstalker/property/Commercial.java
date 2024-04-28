package com.nightstalker.property;

import com.nightstalker.people.Manager;

public class Commercial extends Property {
    private int floor;

    public Commercial(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, int floor) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea, furniture);
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        String title = ">>>>Type: Commercial\n";
        String start = super.toString();
        String commercialInformation = "\nfloor: " + floor;
        return title.concat(start).concat(commercialInformation);
    }
}
