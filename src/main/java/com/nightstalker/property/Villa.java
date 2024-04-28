package com.nightstalker.property;

import com.nightstalker.menu.ASCIIart;
import com.nightstalker.people.Manager;

public class Villa extends Property {

    private int kitchen;
    private int garage;
    private int livingRoom;
    private int room;
    private int floor;
    private double outdoorArea;
    private boolean basement;

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


    public double getOutdoorArea() {
        return outdoorArea;
    }

    public void setOutdoorArea(double outdoorArea) {
        this.outdoorArea = outdoorArea;
    }

    public boolean isBasement() {
        return basement;
    }

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
