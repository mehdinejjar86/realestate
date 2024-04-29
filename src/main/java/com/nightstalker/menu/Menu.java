package com.nightstalker.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * The type Menu.
 */
public class Menu implements Serializable {
    final private List<Optional<String>> statement;
    final private Integer numberOfChoices;

    /**
     * Instantiates a new Menu.
     *
     * @param statement the statement
     */
    public Menu(List<Optional<String>> statement) {
        if (statement == null || statement.isEmpty())
            statement = handleEmpty();
        this.statement = statement;
        this.numberOfChoices = statement.size();
    }

    private List<Optional<String>> handleEmpty() {
        List<Optional<String>> empty = new ArrayList<>();
        empty.add(Optional.of("Empty"));
        return empty;
    }

    /**
     * Prompt integer.
     *
     * @param scanner the scanner
     * @return the integer
     */
    public Integer prompt(Scanner scanner) {
        IntStream.range(0, numberOfChoices)
                .mapToObj((digit) -> String.format("%d %s", digit+1, statement.get(digit).orElse("Unknown")))
                .forEach(System.out::println);
        while(true) {
            System.out.print(">> ");
            String choiceS = scanner.nextLine();
            try {
                int choiceI = Integer.parseInt(choiceS);
                if (choiceI <= numberOfChoices && choiceI > 0) {
                    return choiceI;
                }
                else {
                    System.out.printf("Invalid Choice: \"%d\" is not a valid choices%n", choiceI);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid Choice: %s%n", e.getMessage());;
            }

        }
    }
}
