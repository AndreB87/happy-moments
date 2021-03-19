package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.entities.IEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public abstract class AbstractDatabaseService<T extends IEntity> implements IDatabaseService<T> {

    private EntityManagerFactory entityManagerFactory = Persistence
        .createEntityManagerFactory("de.ansaru.happymoments");

    public abstract T get(long id);

    protected T getSingleResult(String namedQuery, Class clazz, Map<String, Object> params) {
        try {
            EntityManager entityManager = getEntityManager();
            TypedQuery<T> query = entityManager
                .createNamedQuery(namedQuery, clazz);
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                query.setParameter(pair.getKey(), pair.getValue());
            }
            T result = query.getSingleResult();
            entityManager.clear();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    protected List<T> getResultList(String namedQuery, Class clazz, Map<String, Object> params) {
        try {
            EntityManager entityManager = getEntityManager();
            TypedQuery<T> query = entityManager
                .createNamedQuery(namedQuery, clazz);
            for (Map.Entry<String, Object> pair : params.entrySet()) {
                query.setParameter(pair.getKey(), pair.getValue());
            }
            List<T> result = query.getResultList();
            entityManager.clear();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public T create(T entity) {
        try {
            EntityManager entityManager = getEntityManager();
            beginTransaction(entityManager);
            entityManager.persist(entity);
            commit(entityManager);

            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    public T update(T entity) {
        EntityManager entityManager = getEntityManager();
        beginTransaction(entityManager);
        T result = entityManager.merge(entity);
        commit(entityManager);
        return result;
    }

    public boolean delete(long id) {
        IEntity entity = get(id);
        return delete(entity);
    }

    @Override
    public boolean delete(IEntity entity) {
        try {
            EntityManager entityManager = getEntityManager();
            beginTransaction(entityManager);
            entityManager.remove(entity);
            commit(entityManager);
            return !entityManager.contains(entity);
        } catch (Exception e) {
            return false;
        }
    }

    private void beginTransaction(EntityManager entityManager) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    private void commit(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    private void persist(T entity) {
        EntityManager entityManager = getEntityManager();
        beginTransaction(entityManager);
        entityManager.persist(entity);
        commit(entityManager);
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
