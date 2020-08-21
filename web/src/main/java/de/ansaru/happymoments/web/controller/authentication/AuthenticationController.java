package de.ansaru.happymoments.web.controller.authentication;

import de.ansaru.happymoments.model.AbstractController;
import de.ansaru.happymoments.services.AuthenticationService;
import de.ansaru.happymoments.services.OneTimePadService;
import de.ansaru.happymoments.services.UserService;
import de.ansaru.happymoments.web.controller.user.utils.de.Error;
import de.ansaru.happymoments.web.email.impl.EmailServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthenticationController extends AbstractController {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private OneTimePadService oneTimePadService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailServiceImpl emailService;

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
            Model model,
            HttpServletResponse response) throws IOException {

        model.addAttribute("title", "Happy Moments");

        switch (authenticationService.login(email, password)) {
            case SUCCESS:
                if (userService.userExist(email)) {
                    return "redirect:/moments";
                } else {
                    return "/user/profile";
                }

            case WRONG_PASSWORD:
                model.addAttribute("error_msg", Error.WRONG_PASSWORD.getText());
                return "/login";

            case UNKNOWN_EMAIL:
                model.addAttribute("error_msg", Error.UNKNOWN_EMAIL.getText());
                return "/login";

            default:
                model.addAttribute("error_msg", Error.UNKNOWN_ERROR.getText());
                return"/login";
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
            Model model) {

        model.addAttribute("title", "Happy Moments");

        switch (authenticationService.register(email, password)) {
            case SUCCESS:
                oneTimePadService.createOtp(email);
                return "index";
            case EMAIL_USED:
                model.addAttribute("error_msg", Error.EMAIL_USED.getText());
                return "signup";
            default:
                model.addAttribute("error_msg", Error.UNKNOWN_ERROR.getText());
                return "signup";
        }
    }

    @GetMapping("/signup/complete")
    public String signup(
            @RequestParam(name = "otp", required = true)
                String otp,
            Model model) {

        String email = oneTimePadService.useOtp(otp);

        if (email == null) {
            model.addAttribute("error_msg", "Das hat leider nicht geklappt.");
            return "signup";
        }
        userService.createUser(email);

        model.addAttribute("info_msg", "Dein Konto wurde bestätigt \nBitte melde dich erneut an.");
        return "login";
    }

    @GetMapping("/user/password/change")
    public String changePassword(Model model) {
        String email = getUsername();
        model.addAttribute("email", email);

        return "user/password/change";
    }

    @PostMapping("/user/password/change")
    public String changePassword(
            @RequestParam(name = "oldpw", required = true)
                String oldPw,
            @RequestParam(name = "newpw", required = true)
                String newPw,
            @RequestParam(name = "conf", required = true)
                String conf,
            Model model) {

        String email = getUsername();

        if (newPw.equals(conf)) {
            switch (authenticationService.changePassword(email, oldPw, newPw)) {
                case WRONG_PASSWORD:
                    model.addAttribute("error_msg", Error.WRONG_PASSWORD);
                    break;
                case SUCCESSFUL:
                    try {
                        emailService.sendHtmlMessage(email, "Test", "<h3>Hello World</h3\n<a href=\"localhost:8080/login\">Link</a>");
                    } catch (MessagingException e) {
                        LOGGER.error("Fehler beim Versenden einer EMail.");
                    }
                    model.addAttribute("info_msg", "Das Passwort wurde erfolgreich geändert");
                    break;
                default:
                    model.addAttribute("error_msg", Error.UNKNOWN_ERROR);
            }
            return "user/password/change";
        }

        model.addAttribute("error_msg", Error.CONF_FAILED);
        return "user/password/change";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "login";
    }
}
