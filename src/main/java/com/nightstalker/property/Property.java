package com.nightstalker.property;

import com.nightstalker.interaction.ProfileRating;
import com.nightstalker.interaction.PropertyRating;
import com.nightstalker.menu.ASCIIart;
import com.nightstalker.people.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Property implements Serializable {
    private Manager manager;
    private String streetAddress;
    private String lineAddress;
    private PropertyStatus status;
    private Profile owner;
    private Profile renter;
    private BigDecimal price;

    private boolean isAvailable = true;
    private Profile bidder;

    private List<PropertyRating> rating = new ArrayList<>();

    private List<Optional<HouseKeeper>> housekeeper = new ArrayList<>();
    private List<Optional<Contractor>> contractor = new ArrayList<>();

    private int bathroom;
    private double floorArea;
    private boolean furniture;



    private LocalDate dateOfListing;
    private LocalDate endAvailability;

    public Property(Manager manager, String streetAddress, String lineAddress, int room, int bathroom, double floorArea, boolean furniture) {
        this.manager = manager;
        this.streetAddress = streetAddress;
        this.lineAddress = lineAddress;
        this.bathroom = bathroom;
        this.floorArea = floorArea;
        this.furniture = furniture;
        this.dateOfListing = LocalDate.now();
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLineAddress() {
        return lineAddress;
    }

    public void setLineAddress(String lineAddress) {
        this.lineAddress = lineAddress;
    }

    public void addHouseKeeper(HouseKeeper houseKeeper) {
        this.housekeeper.add(Optional.ofNullable(houseKeeper));
    }

    public List<Optional<HouseKeeper>> getHousekeeper() {
        return housekeeper;
    }

    public void setHousekeeper(List<Optional<HouseKeeper>> housekeeper) {
        this.housekeeper = housekeeper;
    }

    public List<Optional<Contractor>> getContractor() {
        return contractor;
    }

    public void setContractor(List<Optional<Contractor>> contractor) {
        this.contractor = contractor;
    }


    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Profile getRenter() {
        return renter;
    }

    public void setRenter(Profile renter) {
        this.renter = renter;
    }

    public void removeHouseKeeper(HouseKeeper houseKeeper) {
        this.housekeeper = this.housekeeper.stream()
                .map(r -> r.orElse(null))
                .filter(r -> r == houseKeeper)
                .map(Optional::ofNullable)
                .toList();
    }

    public void addContractor(Contractor contractor) {
        this.contractor.add(Optional.ofNullable(contractor));
    }

    public void removeContractor(Contractor contractor) {
        this.contractor = this.contractor.stream()
                .map(r -> r.orElse(null))
                .filter(r -> r == contractor)
                .map(Optional::ofNullable)
                .toList();
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }


    public double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(double floorArea) {
        this.floorArea = floorArea;
    }

    public boolean isFurniture() {
        return furniture;
    }

    public void setFurniture(boolean furniture) {
        this.furniture = furniture;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void addRating(PropertyRating rating) {
        this.rating.add(rating);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Profile getBidder() {
        return bidder;
    }

    public void setBidder(Profile bidder) {
        this.bidder = bidder;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDate getEndAvailability() {
        return endAvailability;
    }

    public void setEndAvailability(LocalDate endAvailability) {
        this.endAvailability = endAvailability;
    }

    public LocalDate getDateOfListing() {
        return dateOfListing;
    }

    public void setDateOfListing(LocalDate dateOfListing) {
        this.dateOfListing = dateOfListing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property property)) return false;
        return Objects.equals(manager, property.manager) && Objects.equals(streetAddress, property.streetAddress) && Objects.equals(lineAddress, property.lineAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manager, streetAddress, lineAddress);
    }

    @Override
    public String toString() {

        String art = ASCIIart.property();
        String manager = this.manager != null ? this.manager.getFirstName() + " " + this.manager.getLastName() : "Not assigned";
        String managerReview = "[]";
        if (manager != null)
            managerReview = this.manager.getRating().toString();
        String information = "Manager: " + manager +
                             "\nStreet Address: " + streetAddress +
                             "\nLine Address: " + lineAddress +
                             "\nStatus: " + status +
                             "\nOccupied: " + (owner != null ? owner.getFirstName() + " " + owner.getLastName() : "Not assigned") +
                             "\nHouse keeper: " + housekeeper +
                             "\nContractor: " + contractor +
                             "\nRating: " + rating +
                             "\nBathroom: " + bathroom +
                             "\nFloor Area: " + floorArea +
                             "\nFurniture: " + furniture +
                             "\nDate of Listing: " + dateOfListing +
                             "\nPricing: " + NumberFormat.getCurrencyInstance().format(price);

        return art.concat(information);
    }
}
