package de.ansaru.happymoments.services.user;

import de.ansaru.happymoments.services.user.enums.LoginResult;
import de.ansaru.happymoments.services.user.enums.PasswordChangeResult;
import de.ansaru.happymoments.services.user.enums.RegisterResult;

public interface IAuthenticationService {

    LoginResult login(String email, String passwort);

    RegisterResult register(String email, String password);

    PasswordChangeResult changePassword(String email, String oldPassword, String newPassword);

}
