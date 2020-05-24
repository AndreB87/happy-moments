package de.ansaru.happymoments.model.utils;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationGrantedAuthority implements GrantedAuthority {

    private String authority;

    public AuthenticationGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
