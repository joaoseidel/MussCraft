package com.musscraft.utils;

public class PasswordUtils {

    private static final String HASH_TYPE = "SHA1";

    public static String hashPassword(String password) {
        return Hash.getHash(password, HASH_TYPE);
    }

    public static boolean comparePassword(String password, String hashedPassword) {
        return hashPassword(password).equals(hashedPassword);
    }

}
