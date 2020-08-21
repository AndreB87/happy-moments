package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.DatabaseServiceFactory;
import de.ansaru.happymoments.database.OneTimePadDatabaseService;
import de.ansaru.happymoments.database.utils.EntityType;
import de.ansaru.happymoments.model.OneTimePad;
import de.ansaru.happymoments.services.utils.OneTimePadUtils;

import java.util.Optional;

public class OneTimePadService {

    private static final int OTP_LENGTH = 32;

    private final OneTimePadDatabaseService db;
    private final OneTimePadUtils otpUtils;

    public OneTimePadService() {
        db = (OneTimePadDatabaseService) DatabaseServiceFactory.getService(EntityType.OTP);
        otpUtils = new OneTimePadUtils();
    }

    public boolean createOtp(String email) {
        String otp = otpUtils.createOtp(OTP_LENGTH);

        OneTimePad model = new OneTimePad.Builder()
                .withEmail(email)
                .withOtp(otp)
                .build();

        return db.create(model).isPresent();
    }

    public String useOtp(String otp) {
        Optional<OneTimePad> model = db.getByOtp(otp);
        return model.map(OneTimePad::getEmail).orElse(null);
    }

    public boolean deleteOtp(String otp) {
        Optional<OneTimePad> model = db.getByOtp(otp);
        return model.isPresent() && db.delete(model.get().getId());
    }

}
