package de.ansaru.happymoments.database.user;

import de.ansaru.happymoments.database.AbstractDatabaseService;
import de.ansaru.happymoments.database.user.IOneTimePadDatabaseService;
import de.ansaru.happymoments.database.user.entities.OneTimePadEntity;

import java.util.HashMap;
import java.util.Map;

public class OneTimePadDatabaseService extends AbstractDatabaseService<OneTimePadEntity> implements IOneTimePadDatabaseService {

    public OneTimePadEntity getByOtp(String otp) {
        Map<String, Object> params = new HashMap<>();
        params.put("otp", otp);
        return getSingleResult("otp.findByOtp", params);
    }

    @Override
    public OneTimePadEntity get(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getSingleResult("otp.findById", params);
    }

    private OneTimePadEntity getSingleResult(String namedQuery, Map<String, Object> params) {
        return getSingleResult(namedQuery, OneTimePadEntity.class, params);
    }
}
