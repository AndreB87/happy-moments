package de.ansaru.happymoments.services.user.converter;

import de.ansaru.happymoments.database.user.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsConverter implements IUserDetailsConverter {

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails fromEntity(UserEntity entity) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        return new User(entity.getEmail(), entity.getPassword(), grantedAuthorityList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity toEntity(UserDetails model) {
        UserEntity entity = new UserEntity();
        entity.setEmail(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setAdmin(false);
        return entity;
    }
}
