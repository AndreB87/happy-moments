package de.ansaru.happymoments.database;

import de.ansaru.happymoments.database.daos.AuthenticationDao;
import de.ansaru.happymoments.database.utils.AuthenticationDaoModelConverter;
import de.ansaru.happymoments.model.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.NoResultException;
import java.io.EOFException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthenticationDatabaseService extends AbstractDatabaseService<Authentication> {

    private final AuthenticationDaoModelConverter converter = new AuthenticationDaoModelConverter();

    public Optional<Authentication> getByEmail(String email) {
        try {
            AuthenticationDao dao = entityManager
                    .createNamedQuery("authentication.findByEmail", AuthenticationDao.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Authentication> get(long id) {
        try {
            AuthenticationDao dao = entityManager
                    .createNamedQuery("authentication.findById", AuthenticationDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Authentication> create(Authentication model) {
        try {
            beginTransaction();
            AuthenticationDao dao = converter.toEntity(model);
            entityManager.persist(dao);
            entityManager.getTransaction().commit();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Authentication> update(Authentication model) {
        try {
            AuthenticationDao dao = entityManager
                    .createNamedQuery("authentication.findByEmail", AuthenticationDao.class)
                    .setParameter("email", model.getUsername())
                    .getSingleResult();

            beginTransaction();

            dao.setUsername(model.getUsername());
            dao.setPassword(model.getPassword());
            dao.setAccountExpired(!model.isAccountNonExpired());
            dao.setAccountLocked(!model.isAccountNonLocked());
            dao.setCredentialsExpired(!model.isCredentialsNonExpired());
            dao.setEnabled(model.isEnabled());
            dao.setAuthorities(model.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

            entityManager.getTransaction().commit();

            return Optional.ofNullable(converter.fromEntity(dao));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            AuthenticationDao dao = entityManager
                    .createNamedQuery("authentication.findById", AuthenticationDao.class)
                    .setParameter("id", id)
                    .getSingleResult();

            beginTransaction();
            entityManager.remove(dao);
            entityManager.getTransaction().commit();

            return !entityManager.contains(dao);
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean delete(String email) {
        try {
            AuthenticationDao dao = entityManager
                .createNamedQuery("authentication.findByEmail", AuthenticationDao.class)
                .setParameter("email", email)
                .getSingleResult();

            beginTransaction();
            entityManager.remove(dao);
            entityManager.getTransaction().commit();

            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
