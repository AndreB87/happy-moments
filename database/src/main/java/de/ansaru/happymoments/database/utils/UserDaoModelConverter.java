package de.ansaru.happymoments.database.utils;

import de.ansaru.happymoments.database.daos.UserDao;
import de.ansaru.happymoments.model.User;

public class UserDaoModelConverter implements IDaoModelConverter<UserDao, User> {

    @Override
    public User fromEntity(UserDao dao) {
        return dao == null ?
                null :
                new User.Builder()
                    .withId(dao.getUserId())
                    .withName(dao.getName())
                    .withEmail(dao.getEmail())
                    .asAdmin(dao.isAdmin())
                    .asActive(dao.isActive())
                    .withAccountCreationDate(dao.getAccountCreationDate())
                    .build();
    }
    @Override
    public UserDao toEntity(User model) {
        if (model == null) {
            return null;
        }

        UserDao dao = new UserDao();

        dao.setUserId(model.getId());
        dao.setName(model.getName());
        dao.setEmail(model.getEmail());
        dao.setAdmin(model.isAdmin());
        dao.setActive(model.isActive());
        dao.setAccountCreationDate(model.getAccountCreationDate());

        return dao;
    }
}
