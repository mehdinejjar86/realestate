package com.nightstalker.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressManager {
    public static String addressRegex = "^[A-Za-z\\d]+.*";

    public static boolean validateAddress(String address) {
        Pattern addressPattern = Pattern.compile(addressRegex);
        Matcher addressdMatcher = addressPattern.matcher(address);

        return addressdMatcher.find();
    }
}
