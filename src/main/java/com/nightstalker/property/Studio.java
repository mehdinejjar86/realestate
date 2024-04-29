package com.nightstalker.property;

import com.nightstalker.people.Manager;

/**
 * The type Studio.
 */
public class Studio extends Property {

    private boolean kitchen;
    private boolean balkony;

    /**
     * Instantiates a new Studio.
     *
     * @param manager       the manager
     * @param streetAddress the street address
     * @param lineAddress   the line address
     * @param room          the room
     * @param bathroom      the bathroom
     * @param floorArea     the floor area
     * @param furniture     the furniture
     * @param kitchen       the kitchen
     * @param balkony       the balkony
     */
    public Studio(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, boolean kitchen, boolean balkony) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea, furniture);
        this.kitchen = kitchen;
        this.balkony = balkony;
    }

    /**
     * Is kitchen boolean.
     *
     * @return the boolean
     */
    public boolean isKitchen() {
        return kitchen;
    }

    /**
     * Sets kitchen.
     *
     * @param kitchen the kitchen
     */
    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    /**
     * Is balkony boolean.
     *
     * @return the boolean
     */
    public boolean isBalkony() {
        return balkony;
    }

    /**
     * Sets balkony.
     *
     * @param balkony the balkony
     */
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
