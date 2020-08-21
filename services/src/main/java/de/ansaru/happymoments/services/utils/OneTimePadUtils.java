package de.ansaru.happymoments.services.utils;

import java.util.Random;

public class OneTimePadUtils {

    public static final Random RAND = new Random();

    public String createOtp(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String values = characters.toLowerCase() + characters.toUpperCase() + numbers;

        String result = "";
        for (int i = 0; i < length; i++) {
            result += values.charAt(RAND.nextInt(values.length()));
        }
        return result;
    }
}
