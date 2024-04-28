package com.nightstalker.property;

import com.nightstalker.people.Manager;

public class Apartment extends Property {
    private boolean garage;
    private int kitchen;
    private int livingRoom;
    private int room;

    public Apartment(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture, boolean garage, int kitchen, int livingRoom) {
        super(manager, streetAddress, lineAddress, room, bathroom, floorArea, furniture);
        this.garage = garage;
        this.kitchen = kitchen;
        this.livingRoom = livingRoom;
        this.room = room;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public int getKitchen() {
        return kitchen;
    }

    public void setKitchen(int kitchen) {
        this.kitchen = kitchen;
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
