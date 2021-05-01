package de.ansaru.happymoments.services.user.converter;

import de.ansaru.happymoments.database.user.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDetailsConverter {

    /**
     * Creates the user details from a given user entity
     *
     * @param entity
     *      the user entity
     * @return
     *      the user details created by the entity
     */
    UserDetails fromEntity(UserEntity entity);

    /**
     * Creates a new user entity from UserDetails
     *
     * @param userDetails
     *      the user details
     * @return
     *      the created user entity
     */
    UserEntity toEntity(UserDetails userDetails);

}
