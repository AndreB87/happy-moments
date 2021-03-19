package de.ansaru.happymoments.services.user;

import de.ansaru.happymoments.model.user.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> createUser(String email);

    Optional<User> getUserByEmail(String email);

    Optional<Long> getUserIdByEmail(String email);

    boolean updateInformation(long id, String name);

    boolean userExist(String email);

}
