package de.ansaru.happymoments.database.user;

import de.ansaru.happymoments.database.IDatabaseService;
import de.ansaru.happymoments.database.user.entities.OneTimePadEntity;

public interface IOneTimePadDatabaseService extends IDatabaseService<OneTimePadEntity> {
    OneTimePadEntity getByOtp(String otp);
}
