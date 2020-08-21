package de.ansaru.happymoments.web.configuration;

import de.ansaru.happymoments.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

@Configuration
@PropertySource("classpath:mail.properties")
public class BeanConfiguration {

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String smtpStartTlsEnable;

    @Value("${mail.transport.protocol}")
    private String mailTransportProtocol;

    @Value("${mail.debug}")
    private String mailDebug;

    @Bean
    public BCryptPasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationService createAuthenticationService() {
        return new AuthenticationService();
    }

    @Bean
    public MomentService createMomentService() {
        return new MomentService();
    }

    @Bean
    MomentFileService createMomentFileService() {
        return new MomentFileService();
    }

    @Bean
    public OneTimePadService createOneTimePadService() { return new OneTimePadService(); }

    @Bean
    public UserService createUserService() { return new UserService(); }

    @Bean
    public UserDetailsService createUserDetailService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailTransportProtocol);
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", smtpStartTlsEnable);
        props.put("mail.debug", mailDebug);

        return mailSender;
    }

}
