package de.ansaru.happymoments.services.user.utils;

import de.ansaru.happymoments.services.user.utils.IOneTimePadUtils;

import java.util.Random;

public class OneTimePadUtils implements IOneTimePadUtils {

    public String createOtp(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String values = characters.toLowerCase() + characters.toUpperCase() + numbers;

        String result = "";
        for (int i = 0; i < length; i++) {
            result += values.charAt(new Random().nextInt(values.length()));
        }
        return result;
    }
}
