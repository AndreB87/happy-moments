package de.ansaru.happymoments.database.user;

import de.ansaru.happymoments.database.AbstractDatabaseService;
import de.ansaru.happymoments.database.user.IUserDatabaseService;
import de.ansaru.happymoments.database.user.entities.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

public class UserDatabaseService extends AbstractDatabaseService<UserEntity> implements IUserDatabaseService {

    @Override
    public UserEntity get(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getSingleResult("user.findById", params);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return getSingleResult("user.findByEmail", params);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        try {
            EntityManager entityManager = getEntityManager();
            Long id = entityManager
                    .createNamedQuery("user.findIdByEmail", Long.class)
                    .setParameter("email", email)
                    .getSingleResult();
            entityManager.clear();
            return id;

        } catch (NoResultException e) {
            return null;
        }
    }

    private UserEntity getSingleResult(String namedQuery, Map<String, Object> params) {
        return getSingleResult(namedQuery, UserEntity.class, params);
    }
}
