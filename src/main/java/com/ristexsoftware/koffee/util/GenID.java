package com.ristexsoftware.koffee.util;

import java.util.zip.CRC32;

class Luhn {
    /**
     * Checks if the card is valid
     * 
     * @param card {@link String} card number
     * @return result {@link boolean} true of false
     */
    public static boolean luhnCheck(String card) {
        if (card == null)
            return false;

        char checkDigit = card.charAt(card.length() - 1);
        String digit = calculateCheckDigit(card.substring(0, card.length() - 1));
        return checkDigit == digit.charAt(0);
    }

    /**
     * Calculates the last digits for the card number received as parameter
     * 
     * @param card {@link String} number
     * @return {@link String} the check digit
     */
    public static String calculateCheckDigit(String card) {
        if (card == null)
            return null;

        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[card.length()];

        for (int i = 0; i < card.length(); i++)
            digits[i] = Character.getNumericValue(card.charAt(i));

        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2) {
            digits[i] += digits[i];

            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            if (digits[i] >= 10)
                digits[i] = digits[i] - 9;
        }

        int sum = 0;
        for (int i = 0; i < digits.length; i++)
            sum += digits[i];

        /* multiply by 9 step */
        sum = sum * 9;

        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }
}

public class GenID {
    /**
     * Generate a punishment ID unique for the punishment.
     * 
     * @param key An identifier to start the ID with (usually the position in the
     *            SQL table)
     * @return a Luhn-passable identifier
     */
    public static String generateID(int key) {
        CRC32 crc = new CRC32();

        crc.update((int) (System.currentTimeMillis() / 1000L));
        // Some kind of semi-unique identifier (like last database row insertion)
        crc.update(key);

        // Get the hash
        String crc32hash = Long.toHexString(crc.getValue());
        // Calculate the Luhn check digit
        crc32hash += Luhn.calculateCheckDigit(crc32hash);
        // return.
        return crc32hash.toUpperCase();
    }

    /**
     * Generate a punishment ID unique for the punishment.
     * 
     * @return a Luhn-passable identifier
     */
    public static String generateID() {
        CRC32 crc = new CRC32();

        crc.update((int) (System.nanoTime()));

        // Get the hash
        String crc32hash = Long.toHexString(crc.getValue());
        // Calculate the Luhn check digit
        crc32hash += Luhn.calculateCheckDigit(crc32hash);
        // return.
        return crc32hash.toUpperCase();
    }

    /**
     * Validate that a string passes the Luhn test
     * 
     * @param id a string to test against the Luhn algorithm
     * @return Whether the string passes the Luhn test
     */
    public static boolean validateID(String id) {
        return Luhn.luhnCheck(id);
    }
}