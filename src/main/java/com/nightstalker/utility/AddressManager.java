package com.nightstalker.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Address manager.
 */
public class AddressManager {
    /**
     * The constant addressRegex.
     */
    public static String addressRegex = "^[A-Za-z\\d]+.*";

    /**
     * Validate address boolean.
     *
     * @param address the address
     * @return the boolean
     */
    public static boolean validateAddress(String address) {
        Pattern addressPattern = Pattern.compile(addressRegex);
        Matcher addressdMatcher = addressPattern.matcher(address);

        return addressdMatcher.find();
    }
}
