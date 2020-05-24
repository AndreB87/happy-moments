package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.daos.MomentDao;
import de.ansaru.happymoments.database.utils.MomentDaoModelConverter;
import de.ansaru.happymoments.model.Moment;

import javax.persistence.NoResultException;
import java.util.*;
import java.util.stream.Collectors;

public class MomentDatabaseService extends AbstractDatabaseService<Moment> {

    private final MomentDaoModelConverter converter = new MomentDaoModelConverter();

    public List<Moment> getMomentsByOwner(long ownerId) {
        try {
            return entityManager
                    .createNamedQuery("moments.findMomentsByOwner", MomentDao.class)
                    .setParameter("ownerId", ownerId)
                    .getResultList()
                    .stream()
                    .map(converter::fromEntity)
                    .filter(m -> m != null)
                    .collect(Collectors.toList());
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public List<Moment> getMomentsByUser(long userId) {
        try {
            return entityManager
                    .createNamedQuery("moments.findMomentsByUser", MomentDao.class)
                    .setParameter("userId", userId)
                    .getResultList()
                    .stream()
                    .map(converter::fromEntity)
                    .filter(m -> m != null)
                    .collect(Collectors.toList());
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Moment> get(long id) {
        try {
            return Optional.ofNullable(
                    converter.fromEntity(entityManager
                            .createNamedQuery("moments.findMomentById", MomentDao.class)
                            .setParameter("id", id)
                            .getSingleResult())
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Moment> create(Moment model) {
        try {
            MomentDao dao = converter.toEntity(model);

            entityManager.getTransaction().begin();
            entityManager.persist(model);
            entityManager.getTransaction().commit();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Moment> update(Moment entity) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
