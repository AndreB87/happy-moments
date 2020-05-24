package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.AuthenticationDatabaseService;
import de.ansaru.happymoments.model.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailServiceImpl implements UserDetailsService {

    private AuthenticationDatabaseService db = new AuthenticationDatabaseService();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Authentication> result = db.getByEmail(username);
        return result.orElse(null);
    }

}
