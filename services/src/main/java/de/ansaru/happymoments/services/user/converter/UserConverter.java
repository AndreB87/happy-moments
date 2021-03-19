package de.ansaru.happymoments.services.user.converter;

import de.ansaru.happymoments.database.user.entities.UserEntity;
import de.ansaru.happymoments.model.user.User;

public class UserConverter implements IUserConverter {

    @Override
    public User fromEntity(UserEntity dao) {
        return dao == null ?
                null :
                new User.Builder()
                    .withId(dao.getUserId())
                    .withName(dao.getName())
                    .withEmail(dao.getEmail())
                    .asAdmin(dao.isAdmin())
                    .withAccountCreationDate(dao.getAccountCreationDate())
                    .build();
    }
    @Override
    public UserEntity toEntity(User model) {
        if (model == null) {
            return null;
        }

        UserEntity dao = new UserEntity();

        dao.setUserId(model.getId());
        dao.setName(model.getName());
        dao.setEmail(model.getEmail());
        dao.setAdmin(model.isAdmin());
        dao.setAccountCreationDate(model.getAccountCreationDate());

        return dao;
    }
}
