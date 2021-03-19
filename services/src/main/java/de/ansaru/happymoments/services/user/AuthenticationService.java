package de.ansaru.happymoments.services.user;

import de.ansaru.happymoments.database.user.IUserDatabaseService;
import de.ansaru.happymoments.database.user.entities.UserEntity;
import de.ansaru.happymoments.model.user.HappyMomentsUserDetails;
import de.ansaru.happymoments.model.user.AuthenticationGrantedAuthority;
import de.ansaru.happymoments.services.user.converter.IHappyMomentsUserDetailsConverter;
import de.ansaru.happymoments.services.user.enums.LoginResult;
import de.ansaru.happymoments.services.user.enums.PasswordChangeResult;
import de.ansaru.happymoments.services.user.enums.RegisterResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationService implements UserDetailsService, IAuthenticationService {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private IUserDatabaseService dbService;

    @Autowired
    private IHappyMomentsUserDetailsConverter converter;

    public LoginResult login(String email, String password) {
        UserEntity user = dbService.getUserByEmail(email);

        if (user != null) {
            try {
                DaoAuthenticationProvider authenticationProvider = createDaoAuthenticationProvider();
                Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
                SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(auth));
                return LoginResult.SUCCESS;
            } catch (AuthenticationException e) {
                return LoginResult.WRONG_PASSWORD;
            }
        }
        return LoginResult.UNKNOWN_EMAIL;
    }

    public RegisterResult register(String email, String password) {
        if (dbService.getUserByEmail(email) != null) {
            return RegisterResult.EMAIL_USED;
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new AuthenticationGrantedAuthority("all"));

        HappyMomentsUserDetails userDetails = new HappyMomentsUserDetails.Builder()
            .withEmailAddress(email)
            .withPassword(password)
            .withExpiredAccount(false)
            .withExpiredCredentials(false)
            .isEnabled(false)
            .withGrantedAuthorities(authorityList)
            .withLockedAccount(false)
            .build();

        return dbService.create(converter.toEntity(userDetails)) != null ?
            RegisterResult.SUCCESS :
            RegisterResult.UNKNOWN_ERROR;
    }

    public PasswordChangeResult changePassword(String email, String oldPassword, String newPassword) {
        UserEntity user = dbService.getUserByEmail(email);

        if (user != null) {
            try {
                DaoAuthenticationProvider authenticationProvider = createDaoAuthenticationProvider();
                Authentication auth = new UsernamePasswordAuthenticationToken(email, oldPassword);
                authenticationProvider.authenticate(auth);

                user.setPassword(newPassword);

                return dbService.update(user) != null ?
                    PasswordChangeResult.SUCCESSFUL :
                    PasswordChangeResult.UNKNOWN_ERROR;
            } catch (AuthenticationException e) {
                return PasswordChangeResult.WRONG_PASSWORD;
            }
        } else {
            LOGGER.error("Angemeldeter User ist nicht in der Datenbank!");
            return PasswordChangeResult.UNKNOWN_ERROR;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = dbService.getUserByEmail(username);
        if (entity == null) {
            throw new UsernameNotFoundException(username);
        }
        return converter.fromEntity(entity);
    }

    private DaoAuthenticationProvider createDaoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    public void setDbService(IUserDatabaseService db) {
        this.dbService = db;
    }
}
