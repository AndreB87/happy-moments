package de.ansaru.happymoments.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Authentication extends org.springframework.security.core.userdetails.User implements UserDetails, IModel {

    public Authentication(String username, String password, List<GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
    }

    public Authentication(String username, String password, boolean enabled, boolean accountNotExpired, boolean accountNotLocked, boolean credentialsNotExpired, Collection<GrantedAuthority> authorities) {
        super(username, password, enabled, accountNotExpired, credentialsNotExpired, accountNotLocked, authorities);
    }

}
