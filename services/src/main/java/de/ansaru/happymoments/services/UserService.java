package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.DatabaseServiceFactory;
import de.ansaru.happymoments.database.UserDatabaseService;
import de.ansaru.happymoments.database.utils.EntityType;
import de.ansaru.happymoments.model.User;
import org.apache.log4j.Logger;
import org.assertj.core.util.VisibleForTesting;

import java.time.LocalDate;
import java.util.Optional;

public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);
    private final UserDatabaseService db;

    public UserService() {
        db = (UserDatabaseService) DatabaseServiceFactory.getService(EntityType.USER);
    }

    @VisibleForTesting
    UserService(UserDatabaseService db) {
        this.db = db;
    }

    public Optional<User> createUser(String email) {
        User user = new User.Builder()
                .withEmail(email)
                .withAccountCreationDate(LocalDate.now())
                .asAdmin(false)
                .build();

        return db.create(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return db.getUserByEmail(email);
    }

    public Optional<Long> getUserIdByEmail(String email) {
        return db.getUserIdByEmail(email);
    }

    public boolean updateInformation(long id, String name) {
        Optional<User> optionalUser = db.get(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setName(name);

            return db.update(user).isPresent();
        } else {
            return false;
        }
    }

    public boolean userExist(String email) {
        return db.getUserByEmail(email).isPresent();
    }

    public boolean deleteUser(int id) {
        return db.delete(id);
    }

}
