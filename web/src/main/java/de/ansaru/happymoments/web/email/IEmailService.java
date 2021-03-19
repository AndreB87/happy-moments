package de.ansaru.happymoments.web.email;

import javax.mail.MessagingException;

public interface IEmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendHtmlMessage(String to, String subject, String html) throws MessagingException;

}
