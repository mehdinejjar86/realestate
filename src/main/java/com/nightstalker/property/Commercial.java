package com.nightstalker.property;

import com.nightstalker.people.Manager;

/**
 * The type Commercial.
 */
public class Commercial extends Property {
    private int floor;

    /**
     * Instantiates a new Commercial.
     *
     * @param manager       the manager
     * @param streetAddress the street address
     * @param lineAddress   the line address
     * @param room          the room
     * @param bathroom      the bathroom
     * @param floorArea     the floor area
     * @param furniture     the furniture
     * @param floor         the floor
     */
    public Commercial(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, int floor) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea, furniture);
        this.floor = floor;
    }

    /**
     * Gets floor.
     *
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets floor.
     *
     * @param floor the floor
     */
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
