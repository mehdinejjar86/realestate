package com.nightstalker.property;

import com.nightstalker.people.Manager;

public class House extends Property {

    private int kitchen;
    private int garage;
    private int livingRoom;
    private int room;
    private int floor;
    private int porch;
    private boolean basement;

    public House(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, int kitchen, int garage, int livingRoom, int floor, int porch) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea,  furniture);
        this.kitchen = kitchen;
        this.garage = garage;
        this.livingRoom = livingRoom;
        this.room = room;
        this.floor = floor;
        this.porch = porch;
    }

    public int getKitchen() {
        return kitchen;
    }

    public void setKitchen(int kitchen) {
        this.kitchen = kitchen;
    }

    public int getGarage() {
        return garage;
    }

    public void setGarage(int garage) {
        this.garage = garage;
    }

    public int getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(int livingRoom) {
        this.livingRoom = livingRoom;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getPorch() {
        return porch;
    }

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
