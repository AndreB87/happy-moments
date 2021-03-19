package de.ansaru.happymoments.database.user;

import de.ansaru.happymoments.database.IDatabaseService;
import de.ansaru.happymoments.database.user.entities.UserEntity;

public interface IUserDatabaseService extends IDatabaseService<UserEntity> {

    UserEntity getUserByEmail(String email);

    Long getUserIdByEmail(String email);

}
