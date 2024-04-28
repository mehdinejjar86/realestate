package com.nightstalker.people;

import com.nightstalker.property.Property;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public interface PromptPeople {
    List<Optional<Property>> prompt(List<Optional<Property>> property, List<Optional<Profile>> profile,  Scanner scanner);
}
