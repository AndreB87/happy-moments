package de.ansaru.happymoments.web.controller.authentication;

import de.ansaru.happymoments.services.AuthenticationService;
import de.ansaru.happymoments.web.controller.user.utils.de.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Happy Moments");
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(name="email", required=true)
                    String email,
            @RequestParam(name="password", required=true)
                    String password,
            Model model) {

        model.addAttribute("title", "Happy Moments");

        switch (authenticationService.login(email, password)) {
            case SUCCESS:
                model.addAttribute("error_text", null);
                return "loggedin";
            case WRONG_PASSWORD:
                model.addAttribute("error_text", Error.WRONG_PASSWORD.getText());
                return "login";
            case UNKNOWN_EMAIL:
                model.addAttribute("error_text", Error.UNKNOWN_EMAIL.getText());
                return "login";
            default:
                model.addAttribute("error_text", Error.UNKNOWN_ERROR.getText());
                return "login";
        }
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Happy Moments");
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam(name = "email", required = true)
            String email,
            @RequestParam(name = "password", required = true)
            String password,
            Model model
    ) {
        switch (authenticationService.register(email, password)) {
            case SUCCESS:
                return "login";
            case EMAIL_USED:
                model.addAttribute("error_text", Error.EMAIL_USED.getText());
                return "signup";
            default:
                model.addAttribute("error_text", Error.UNKNOWN_ERROR.getText());
                return "signup";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "login";
    }
}
