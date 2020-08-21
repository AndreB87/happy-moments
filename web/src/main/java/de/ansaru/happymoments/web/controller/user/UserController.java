package de.ansaru.happymoments.web.controller.user;

import de.ansaru.happymoments.model.AbstractController;
import de.ansaru.happymoments.model.User;
import de.ansaru.happymoments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/profile")
    public String updateProfile(Model model) {
        String email = getUsername();
        Optional<User> optionalUser = userService.getUserByEmail(email);

        if (optionalUser.isPresent()) {
            model.addAttribute("name", optionalUser.get().getName());
            model.addAttribute("email", optionalUser.get().getEmail());

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

        if (!optionalUser.isPresent()) {
            optionalUser = userService.createUser(email);
            model.addAttribute("email", email);
        }

        if (name == null) {
            model.addAttribute("error_msg", "Der Name muss angegeben werden.");
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

        return "error";
    }

}
