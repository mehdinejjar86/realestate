package com.nightstalker.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Serialize manager.
 *
 * @param <T> the type parameter
 */
public class SerializeManager<T> {
    /**
     * Read list.
     *
     * @param <T>      the type parameter
     * @param filepath the filepath
     * @return the list
     */
    public static <T> List<Optional<T>> read(String filepath) {
        try {
            FileInputStream file = new FileInputStream(filepath);
            ObjectInputStream obj = new ObjectInputStream(file);
            List<T> readObject = (List<T>) obj.readObject();

            System.out.println(readObject);
            System.out.println(filepath + " read Successfully!");

            file.close();
            obj.close();
            return new ArrayList<>(readObject.stream().map(Optional::ofNullable).toList());
        } catch (Exception e)
        {
            System.out.println("Empty " + filepath + " " + e.getMessage());
            return new ArrayList<Optional<T>>();
        }

    }

    /**
     * Write.
     *
     * @param <T>      the type parameter
     * @param filepath the filepath
     * @param array    the array
     */
    public static <T> void write(String filepath, List<Optional<T>> array) {
        try {
            List<T> toBeSaved =  array.stream()
                    .map(p -> p.orElse(null))
                    .filter(Objects::nonNull)
                    .toList();
            FileOutputStream file = new FileOutputStream(filepath);
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(toBeSaved);
            System.out.println(filepath + " Serialized Successfully!");
            file.close();
            obj.close();
        } catch (Exception e)
        {
            System.out.println("Error occurred here:" + e.getMessage());
        }
    }
}
