package com.nightstalker.utility;

import com.nightstalker.people.Buyer;
import com.nightstalker.people.Profile;
import com.nightstalker.property.Property;
import com.nightstalker.property.PropertyStatus;

import java.time.LocalDate;
import java.util.*;

/**
 * The type End auction.
 */
public class EndAuction {
    /**
     * Check auction list.
     *
     * @param property the property
     * @return the list
     */
    public static List<Optional<Property>> checkAuction(List<Optional<Property>> property) {
        return new ArrayList<>(property.stream()
                .map(p -> p.orElse(null))
                .filter(Objects::nonNull)
                .peek(p -> {  if (p.getStatus() == PropertyStatus.AUCTION
                        && LocalDate.now().isAfter(p.getEndAvailability())
                        && p.getBidder() != null){
                    p.setOwner(p.getBidder());
                    p.setBidder(null);
                    p.setManager(null);
                    p.setStatus(PropertyStatus.SOLD);
                     }
                })
                .map(Optional::ofNullable)
                .toList());
    }
}
