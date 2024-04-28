package com.nightstalker.utility;

import com.nightstalker.property.Property;
import com.nightstalker.property.PropertyStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EndRent {
    public static List<Optional<Property>> checkRent(List<Optional<Property>> property) {
        return property.stream()
                .map(p -> p.orElse(null))
                .filter(Objects::nonNull)
                .filter(p -> p.getStatus() == PropertyStatus.FOR_RENT)
                .filter(p -> LocalDate.now().isAfter(p.getEndAvailability()))
                .map(p -> { Property nP = p;
                    nP.setOwner(null);
                    nP.setAvailable(true);
                    return nP;
                })
                .map(Optional::ofNullable)
                .toList();
    }
}
