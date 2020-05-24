package de.ansaru.happymoments.services;

import de.ansaru.happymoments.database.AuthenticationDatabaseService;
import de.ansaru.happymoments.model.Authentication;
import de.ansaru.happymoments.model.utils.AuthenticationGrantedAuthority;
import de.ansaru.happymoments.services.enums.LoginResult;
import de.ansaru.happymoments.services.enums.PasswordChangeResult;
import de.ansaru.happymoments.services.enums.RegisterResult;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthenticationService {

    private final AuthenticationDatabaseService db = new AuthenticationDatabaseService();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginResult login(String email, String password) {
        Optional<Authentication> user = db.getByEmail(email);

        if (user.isPresent()) {
            try {
                DaoAuthenticationProvider authenticationProvider = createDaoAuthenticationProvider();
                org.springframework.security.core.Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
                SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(auth));
                return LoginResult.SUCCESS;
            } catch (AuthenticationException e) {
                return LoginResult.WRONG_PASSWORD;
            }
        }
        return LoginResult.UNKNOWN_EMAIL;
    }

    public RegisterResult register(String email, String password) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new AuthenticationGrantedAuthority("all"));

        Authentication userAuth = new Authentication(email, encoder.encode(password), authorityList);

        if (db.getByEmail(email).isPresent()) {
            return RegisterResult.EMAIL_USED;
        }

        return db.create(userAuth).isPresent() ?
                RegisterResult.SUCCESS :
                RegisterResult.UNKNOWN_ERROR;
    }

    public PasswordChangeResult changePassword(String email, String oldPassword, String newPassword) {
        Optional<Authentication> optionalUser = db.getByEmail(email);

        if (optionalUser.isPresent()) {
            Authentication user = optionalUser.get();
            if (encoder.encode(oldPassword).equals(user.getPassword())) {
                Authentication updated = new Authentication(
                    user.getUsername(),
                    encoder.encode(newPassword),
                    new ArrayList<>(user.getAuthorities())
                );
                return db.update(updated).isPresent() ?
                    PasswordChangeResult.SUCCESSFUL :
                    PasswordChangeResult.UNKNOWN_ERROR;
            } else {
                return PasswordChangeResult.WRONG_PASSWORD;
            }
        } else {
            return PasswordChangeResult.UNKNOWN_ERROR;
        }
    }

    private DaoAuthenticationProvider createDaoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new UserDetailServiceImpl());
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }
}
