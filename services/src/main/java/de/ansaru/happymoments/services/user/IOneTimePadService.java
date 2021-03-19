package de.ansaru.happymoments.services.user;

public interface IOneTimePadService {

    boolean createOtp(String email);

    String useOtp(String otp);

    boolean deleteOtp(String otp);

}
