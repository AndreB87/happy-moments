package de.ansaru.happymoments.database.user;

import de.ansaru.happymoments.database.AbstractDatabaseService;
import de.ansaru.happymoments.database.user.constants.OneTimePadQueries;
import de.ansaru.happymoments.database.user.entities.OneTimePadEntity;

import java.util.HashMap;
import java.util.Map;

public class OneTimePadDatabaseService extends AbstractDatabaseService<OneTimePadEntity> implements IOneTimePadDatabaseService {

    public OneTimePadEntity getByOtp(String otp) {
        Map<String, Object> params = new HashMap<>();
        params.put("otp", otp);
        return getSingleResult(OneTimePadQueries.FIND_BY_OTP, params);
    }

    @Override
    public OneTimePadEntity get(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getSingleResult(OneTimePadQueries.FIND_BY_ID, params);
    }

    private OneTimePadEntity getSingleResult(String namedQuery, Map<String, Object> params) {
        return getSingleResult(namedQuery, OneTimePadEntity.class, params);
    }
}
