package com.nightstalker.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The type Create list.
 *
 * @param <T> the type parameter
 */
public class CreateList<T> {
    /**
     * Create list.
     *
     * @param <T>   the type parameter
     * @param items the items
     * @return the list
     */
    @SafeVarargs
    public static <T> List<Optional<T>> create(T... items)
    {
        return Arrays.stream(items)
                .map(Optional::ofNullable).toList();
    }
}
