package de.ansaru.happymoments.web.email;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

}
