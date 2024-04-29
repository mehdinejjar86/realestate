package com.nightstalker.property;

import com.nightstalker.menu.ASCIIart;
import com.nightstalker.people.Manager;

/**
 * The type Villa.
 */
public class Villa extends Property {

    private int kitchen;
    private int garage;
    private int livingRoom;
    private int room;
    private int floor;
    private double outdoorArea;
    private boolean basement;

    /**
     * Instantiates a new Villa.
     *
     * @param manager       the manager
     * @param streetAddress the street address
     * @param lineAddress   the line address
     * @param room          the room
     * @param bathroom      the bathroom
     * @param floorArea     the floor area
     * @param furniture     the furniture
     * @param kitchen       the kitchen
     * @param garage        the garage
     * @param livingRoom    the living room
     * @param floor         the floor
     * @param outdoorArea   the outdoor area
     * @param basement      the basement
     */
    public Villa(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, int kitchen, int garage, int livingRoom, int floor, double outdoorArea, boolean basement) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea, furniture);
        this.kitchen = kitchen;
        this.garage = garage;
        this.livingRoom = livingRoom;
        this.room = room;
        this.floor = floor;
        this.outdoorArea = outdoorArea;
        this.basement = basement;
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
     * Gets garage.
     *
     * @return the garage
     */
    public int getGarage() {
        return garage;
    }

    /**
     * Sets garage.
     *
     * @param garage the garage
     */
    public void setGarage(int garage) {
        this.garage = garage;
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


    /**
     * Gets outdoor area.
     *
     * @return the outdoor area
     */
    public double getOutdoorArea() {
        return outdoorArea;
    }

    /**
     * Sets outdoor area.
     *
     * @param outdoorArea the outdoor area
     */
    public void setOutdoorArea(double outdoorArea) {
        this.outdoorArea = outdoorArea;
    }

    /**
     * Is basement boolean.
     *
     * @return the boolean
     */
    public boolean isBasement() {
        return basement;
    }

    /**
     * Sets basement.
     *
     * @param basement the basement
     */
    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    @Override
    public String toString() {
        String title = ">>>>Type: Villa\n";
        String start = super.toString();
        String villaInformation = "\nkitchen: " + kitchen +
                                  "\ngarage: " + garage +
                                  "\nlivingRoom: " + livingRoom +
                                  "\nroom: " + room +
                                  "\nfloor: " + floor +
                                  "\noutdoorArea: " + outdoorArea +
                                  "\nbasement: " + basement;
        return title.concat(start).concat(villaInformation);
    }

}
