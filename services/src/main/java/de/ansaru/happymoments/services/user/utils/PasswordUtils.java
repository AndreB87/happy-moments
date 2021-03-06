package de.ansaru.happymoments.services.user.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class PasswordUtils {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private final SecureRandom secureRandom;

    public PasswordUtils() throws NoSuchAlgorithmException {
        secureRandom = SecureRandom.getInstanceStrong();
    }

    public Optional<String> generateSalt(int length) {
        if (length < 0) {
             return Optional.empty();
        }
        byte[] salt = new byte[length];
        secureRandom.nextBytes(salt);

        return Optional.ofNullable(Base64.getEncoder().encodeToString(salt));
    }

    public Optional<String> hashPassword(String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    public boolean verifyPassword (String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password, salt);
        return optEncrypted.map(s -> s.equals(key)).orElse(false);
    }
}
