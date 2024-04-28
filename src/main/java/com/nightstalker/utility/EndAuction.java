package com.nightstalker.utility;

import com.nightstalker.people.Buyer;
import com.nightstalker.people.Profile;
import com.nightstalker.property.Property;
import com.nightstalker.property.PropertyStatus;

import java.time.LocalDate;
import java.util.*;

public class EndAuction {
    public static List<Optional<Property>> checkAuction(List<Optional<Property>> property) {
        return property.stream()
                .map(p -> p.orElse(null))
                .filter(Objects::nonNull)
                .filter(p -> p.getStatus() == PropertyStatus.AUCTION)
                .filter(p -> LocalDate.now().isAfter(p.getEndAvailability()))
                .map(p -> { Property nP = p;
                     nP.setOwner(p.getBidder());
                     nP.setBidder(null);
                     return nP;
                })
                .map(Optional::ofNullable)
                .toList();
    }
}
