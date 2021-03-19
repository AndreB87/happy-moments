package de.ansaru.happymoments.services.user;

import de.ansaru.happymoments.database.user.IOneTimePadDatabaseService;
import de.ansaru.happymoments.database.user.entities.OneTimePadEntity;
import de.ansaru.happymoments.services.user.utils.IOneTimePadUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class OneTimePadService implements IOneTimePadService {

    private static final int OTP_LENGTH = 32;

    @Autowired
    private IOneTimePadUtils otpUtils;

    @Autowired
    private IOneTimePadDatabaseService db;

    public boolean createOtp(String email) {
        String otp = otpUtils.createOtp(OTP_LENGTH);
        OneTimePadEntity model = new OneTimePadEntity();
        model.setEmail(email);
        model.setOtp(otp);
        return db.create(model) != null;
    }

    public String useOtp(String otp) {
        OneTimePadEntity entity = db.getByOtp(otp);
        return entity == null ? null : entity.getEmail();
    }

    public boolean deleteOtp(String otp) {
        OneTimePadEntity entity = db.getByOtp(otp);
        return entity != null && db.delete(entity.getId());
    }

}
