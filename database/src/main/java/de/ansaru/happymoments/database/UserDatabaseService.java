package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.daos.UserDao;
import de.ansaru.happymoments.database.utils.UserDaoModelConverter;
import de.ansaru.happymoments.model.User;

import javax.persistence.NoResultException;
import java.util.Optional;

public class UserDatabaseService extends AbstractDatabaseService<User> {

    private final UserDaoModelConverter converter = new UserDaoModelConverter();

    public Optional<User> getUserByEmail(String email) {
        try {
            UserDao entity = entityManager
                    .createNamedQuery("user.findByEmail", UserDao.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.ofNullable(converter.fromEntity(entity));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> get(long id) {
        try {
            return Optional.ofNullable(
                converter.fromEntity(
                    entityManager
                        .createNamedQuery("user.getById", UserDao.class)
                        .setParameter("id", id)
                        .getSingleResult()
                )
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> create(User model) {
        try {
            UserDao dao = converter.toEntity(model);

            entityManager.getTransaction().begin();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> update(User user) {
        try {
            UserDao entity = entityManager
                    .createNamedQuery("user.getById", UserDao.class)
                    .setParameter("id", user.getId())
                    .getSingleResult();

            entityManager.getTransaction().begin();
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
            entityManager.getTransaction().commit();

            return Optional.ofNullable(converter.fromEntity(entity));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            UserDao entity = entityManager.createNamedQuery("user.getById", UserDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();

            return !entityManager.contains(entity);
        } catch (NoResultException e) {
            return false;
        }
    }
}
