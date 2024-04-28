package com.nightstalker.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CreateList<T> {
    @SafeVarargs
    public static <T> List<Optional<T>> create(T... items)
    {
        return Arrays.stream(items)
                .map(Optional::ofNullable).toList();
    }
}
