package de.ansaru.happymoments.services.user.converter;

import de.ansaru.happymoments.database.user.entities.UserEntity;
import de.ansaru.happymoments.model.user.HappyMomentsUserDetails;
import de.ansaru.happymoments.model.user.AuthenticationGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class HappyMomentsUserDetailsConverter implements IHappyMomentsUserDetailsConverter {

    @Override
    public HappyMomentsUserDetails fromEntity(UserEntity entity) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        entity.getAuthorities().forEach(a -> authorityList.add(new AuthenticationGrantedAuthority(a)));
        return new HappyMomentsUserDetails.Builder()
                .withEmailAddress(entity.getEmail())
                .withPassword(entity.getPassword())
                .withExpiredAccount(false)
                .withExpiredCredentials(false)
                .isEnabled(false)
                .withGrantedAuthorities(authorityList)
                .withLockedAccount(false)
                .build();
    }

    @Override
    public UserEntity toEntity(HappyMomentsUserDetails model) {
        List<String> authorityList = new ArrayList<>();
        model.getAuthorities().forEach(a -> authorityList.add(a.getAuthority()));
        UserEntity entity = new UserEntity();
        entity.setEmail(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setAccountExpired(!model.isAccountNonExpired());
        entity.setCredentialsExpired(!model.isCredentialsNonExpired());
        entity.setEnabled(model.isEnabled());
        entity.setAuthorities(authorityList);
        entity.setAccountLocked(!model.isAccountNonLocked());
        return entity;
    }
}
