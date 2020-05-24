package de.ansaru.happymoments.web.controller.user;

import de.ansaru.happymoments.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService = new UserService();

}
