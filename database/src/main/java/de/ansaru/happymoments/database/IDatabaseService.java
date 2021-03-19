package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.entities.IEntity;

public interface IDatabaseService<T extends IEntity> {

    T get(long id);

    T create(T entity);

    T update(T entity);

    boolean delete(long id);

    boolean delete(T entity);
}
