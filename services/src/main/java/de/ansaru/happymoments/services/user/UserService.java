package de.ansaru.happymoments.services.user;

import de.ansaru.happymoments.database.user.IUserDatabaseService;
import de.ansaru.happymoments.database.user.entities.UserEntity;
import de.ansaru.happymoments.model.user.User;
import de.ansaru.happymoments.services.user.converter.IUserConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

public class UserService implements IUserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    @Autowired
    private IUserConverter converter;

    @Autowired
    private IUserDatabaseService dbService;

    public Optional<User> createUser(String email) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setAccountCreationDate(LocalDate.now());
        user.setAdmin(false);
        return Optional.ofNullable(
            converter.fromEntity(dbService.create(user))
        );
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(
            converter.fromEntity(
                dbService.getUserByEmail(email)
            )
        );
    }

    public Optional<Long> getUserIdByEmail(String email) {
        return Optional.ofNullable(dbService.getUserIdByEmail(email));
    }

    public boolean updateInformation(long id, String name) {
        UserEntity user = dbService.get(id);
        if (user != null) {
            user.setName(name);
            return dbService.update(user) != null;
        } else {
            return false;
        }
    }

    public boolean userExist(String email) {
        return this.getUserIdByEmail(email).isPresent();
    }

    public boolean deleteUser(int id) {
        return dbService.delete(id);
    }

    public void setDbService(IUserDatabaseService dbService) {
        this.dbService = dbService;
    }

    public void setConverter(IUserConverter converter) {
        this.converter = converter;
    }
}
