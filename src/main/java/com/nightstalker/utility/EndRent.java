package com.nightstalker.utility;

import com.nightstalker.property.Property;
import com.nightstalker.property.PropertyStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type End rent.
 */
public class EndRent {
    /**
     * Check rent list.
     *
     * @param property the property
     * @return the list
     */
    public static List<Optional<Property>> checkRent(List<Optional<Property>> property) {
        return new ArrayList<>(property.stream()
                .map(p -> p.orElse(null))
                .filter(Objects::nonNull)
                .peek(p -> { if (p.getStatus() == PropertyStatus.FOR_RENT
                                && p.getEndAvailability() != null
                                && LocalDate.now().isAfter(p.getEndAvailability()))
                {
                    p.setOwner(null);
                    p.setAvailable(true);
                }
                })
                .map(Optional::ofNullable)
                .toList());
    }
}
