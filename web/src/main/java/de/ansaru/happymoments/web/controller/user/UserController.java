package de.ansaru.happymoments.web.controller.user;

import de.ansaru.happymoments.model.user.User;
import de.ansaru.happymoments.services.user.IUserService;
import de.ansaru.happymoments.web.controller.AbstractController;
import de.ansaru.happymoments.web.controller.user.utils.de.Error;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/user/profile")
    public String updateProfile(Model model) {
        String email = getUsername();
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            model.addAttribute("name", user.get().getName());
            model.addAttribute("email", user.get().getEmail());

            return "user/profile";
        }
        return "error";
    }

    @PostMapping("/user/profile")
    public String updateProfile(
        @RequestParam(name = "name", required = false)
            String name,
        Model model) {

        String email = getUsername();
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (name == null) {
            model.addAttribute("error_msg", Error.MISSING_NAME.getText());
            LOG.error("missing name from user " + email);
            return "user/profile";
        }
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userService.updateInformation(user.getId(), name)){
                model.addAttribute("name", user.getName());
            } else {
                model.addAttribute("name", name);
            }
            model.addAttribute("email", user.getEmail());
            return "user/profile";
        }
        LOG.error("unknown user with username " + email);
        return "error";
    }

}
