package com.nightstalker.property;

import com.nightstalker.people.Manager;

/**
 * The type House.
 */
public class House extends Property {

    private int kitchen;
    private int garage;
    private int livingRoom;
    private int room;
    private int floor;
    private int porch;
    private boolean basement;

    /**
     * Instantiates a new House.
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
     * @param porch         the porch
     */
    public House(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, int kitchen, int garage, int livingRoom, int floor, int porch) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea,  furniture);
        this.kitchen = kitchen;
        this.garage = garage;
        this.livingRoom = livingRoom;
        this.room = room;
        this.floor = floor;
        this.porch = porch;
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
     * Gets porch.
     *
     * @return the porch
     */
    public int getPorch() {
        return porch;
    }

    /**
     * Sets porch.
     *
     * @param porch the porch
     */
    public void setPorch(int porch) {
        this.porch = porch;
    }

    @Override
    public String toString() {
        String title = ">>>>Type: House\n";
        String start = super.toString();
        String houseInformation = "\nkitchen: " + kitchen +
                                  "\ngarage: " + garage +
                                  "\nlivingRoom: " + livingRoom +
                                  "\nroom: " + room +
                                  "\nfloor: " + floor +
                                  "\nporch: " + porch +
                                  "\nbasement: " + basement;
        return title.concat(start).concat(houseInformation);
    }

}
