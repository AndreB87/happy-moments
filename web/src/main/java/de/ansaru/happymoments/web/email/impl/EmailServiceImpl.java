package de.ansaru.happymoments.web.email.impl;

import de.ansaru.happymoments.web.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.address}")
    private String from;

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendHtmlMessage(String to, String subject, String html) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setText(html, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(from);
        emailSender.send(message);
    }
}
