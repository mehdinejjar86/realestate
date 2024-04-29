package com.nightstalker.utility;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The type Hash password.
 */
public class HashPassword {
    /**
     * Hash password string.
     *
     * @param plainTextPassword the plain text password
     * @return the string
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Check password boolean.
     *
     * @param plainTextPassword the plain text password
     * @param hashedPassword    the hashed password
     * @return the boolean
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
