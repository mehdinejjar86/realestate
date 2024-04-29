package com.nightstalker.property;

import com.nightstalker.people.Manager;

/**
 * The type Apartment.
 */
public class Apartment extends Property {
    private boolean garage;
    private int kitchen;
    private int livingRoom;
    private int room;

    /**
     * Instantiates a new Apartment.
     *
     * @param manager       the manager
     * @param streetAddress the street address
     * @param lineAddress   the line address
     * @param room          the room
     * @param bathroom      the bathroom
     * @param floorArea     the floor area
     * @param furniture     the furniture
     * @param garage        the garage
     * @param kitchen       the kitchen
     * @param livingRoom    the living room
     */
    public Apartment(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, boolean garage, int kitchen, int livingRoom) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea, furniture);
        this.garage = garage;
        this.kitchen = kitchen;
        this.livingRoom = livingRoom;
        this.room = room;
    }

    /**
     * Is garage boolean.
     *
     * @return the boolean
     */
    public boolean isGarage() {
        return garage;
    }

    /**
     * Sets garage.
     *
     * @param garage the garage
     */
    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    /**
     * Gets kitchen.
     *
     * @return the kitchen
     */
    public int getKitchen() {
        return kitchen;
    }

    /**
     * Sets kitchen.
     *
     * @param kitchen the kitchen
     */
    public void setKitchen(int kitchen) {
        this.kitchen = kitchen;
    }

    /**
     * Gets living room.
     *
     * @return the living room
     */
    public int getLivingRoom() {
        return livingRoom;
    }

    /**
     * Sets living room.
     *
     * @param livingRoom the living room
     */
    public void setLivingRoom(int livingRoom) {
        this.livingRoom = livingRoom;
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public int getRoom() {
        return room;
    }

    /**
     * Sets room.
     *
     * @param room the room
     */
    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString() {
        String title = ">>>>Type: Apartment\n";
        String start = super.toString();
        String apartmentInformation = "\nkitchen: " + kitchen +
                                  "\ngarage: " + garage +
                                  "\nlivingRoom: " + livingRoom +
                                  "\nroom: " + room;
        return title.concat(start).concat(apartmentInformation);
    }

}
