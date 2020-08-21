package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.daos.OneTimePadDao;
import de.ansaru.happymoments.database.utils.OneTimePadDaoModelConverter;
import de.ansaru.happymoments.model.OneTimePad;

import javax.persistence.NoResultException;
import java.util.Optional;

public class OneTimePadDatabaseService extends AbstractDatabaseService<OneTimePad> {

    private final OneTimePadDaoModelConverter converter = new OneTimePadDaoModelConverter();

    public Optional<OneTimePad> getByOtp(String otp) {
        try {
            OneTimePadDao dao = entityManager
                    .createNamedQuery("otp.findByOtp", OneTimePadDao.class)
                    .setParameter("otp", otp)
                    .getSingleResult();

            return Option qal.ofNullable(converter.fromEntity(dao));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<OneTimePad> get(long id) {
        try {
            OneTimePadDao dao = entityManager
                    .createNamedQuery("otp.findById", OneTimePadDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<OneTimePad> create(OneTimePad model) {
        OneTimePadDao dao = converter.toEntity(model);

        beginTransaction();
        entityManager.persist(dao);
        entityManager.getTransaction().commit();

        return Optional.ofNullable(converter.fromEntity(dao));
    }

    @Override
    public Optional<OneTimePad> update(OneTimePad model) {
        try {
            OneTimePadDao dao = entityManager
                    .createNamedQuery("otp.findById", OneTimePadDao.class)
                    .setParameter("id", model.getId())
                    .getSingleResult();

            dao.setEmail(model.getEmail());
            dao.setOtp(model.getOtp());
            dao.setActive(model.isActive());

            beginTransaction();
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            OneTimePadDao dao = entityManager.createNamedQuery("otp.findById", OneTimePadDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            entityManager.remove(dao);
            return !entityManager.contains(dao);
        } catch (Exception e) {
            return false;
        }
    }
}
