package de.ansaru.happymoments.database.utils;

import de.ansaru.happymoments.database.daos.AuthenticationDao;
import de.ansaru.happymoments.model.Authentication;
import de.ansaru.happymoments.model.utils.AuthenticationGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

public class AuthenticationDaoModelConverter implements IDaoModelConverter<AuthenticationDao, Authentication> {

    @Override
    public Authentication fromEntity(AuthenticationDao dao) {
        return dao != null ?
            new Authentication(
                    dao.getUsername(),
                    dao.getPassword(),
                    dao.isEnabled(),
                    dao.isAccountNonExpired(),
                    dao.isAccountNonLocked(),
                    dao.isCredentialsNonExpired(),
                    dao.getAuthorities().stream().map(AuthenticationGrantedAuthority::new).collect(Collectors.toList())
            ) :
            null;
    }

    @Override
    public AuthenticationDao toEntity(Authentication model) {
        if (model == null) {
            return null;
        }

        AuthenticationDao dao = new AuthenticationDao();
        dao.setUsername(model.getUsername());
        dao.setPassword(model.getPassword());
        dao.setAccountExpired(!model.isAccountNonExpired());
        dao.setAccountLocked(!model.isAccountNonLocked());
        dao.setCredentialsExpired(!model.isCredentialsNonExpired());
        dao.setEnabled(model.isEnabled());
        dao.setAuthorities(model.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return dao;
    }
}
