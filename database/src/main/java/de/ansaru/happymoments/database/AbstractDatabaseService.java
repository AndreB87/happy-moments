package de.ansaru.happymoments.database;

import de.ansaru.happymoments.model.IModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public abstract class AbstractDatabaseService<T extends IModel> {

    protected EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("de.ansaru.happymoments");;
    protected EntityManager entityManager = entityManagerFactory.createEntityManager();

    public abstract Optional<T> get(long id);

    public abstract Optional<T> create(T model);

    public abstract Optional<T> update(T model);

    public abstract boolean delete(long id);

    protected void beginTransaction() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

}
