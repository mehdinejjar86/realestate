package com.nightstalker.people;

import com.nightstalker.property.Property;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The interface Prompt people.
 */
public interface PromptPeople {
    /**
     * Prompt list.
     *
     * @param property the property
     * @param profile  the profile
     * @param scanner  the scanner
     * @return the list
     */
    List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile,  Scanner scanner);
}
