package de.ansaru.happymoments.web.configuration;

import de.ansaru.happymoments.database.moments.IMomentDatabaseService;
import de.ansaru.happymoments.database.moments.IMomentFileDatabaseService;
import de.ansaru.happymoments.database.user.IOneTimePadDatabaseService;
import de.ansaru.happymoments.database.user.IUserDatabaseService;
import de.ansaru.happymoments.database.moments.MomentDatabaseService;
import de.ansaru.happymoments.database.moments.MomentFileDatabaseService;
import de.ansaru.happymoments.database.user.OneTimePadDatabaseService;
import de.ansaru.happymoments.database.user.UserDatabaseService;
import de.ansaru.happymoments.services.user.AuthenticationService;
import de.ansaru.happymoments.services.user.IAuthenticationService;
import de.ansaru.happymoments.services.user.converter.UserDetailsConverter;
import de.ansaru.happymoments.services.user.converter.IUserDetailsConverter;
import de.ansaru.happymoments.services.moments.IMomentFileService;
import de.ansaru.happymoments.services.moments.IMomentService;
import de.ansaru.happymoments.services.moments.MomentFileService;
import de.ansaru.happymoments.services.moments.MomentService;
import de.ansaru.happymoments.services.moments.converter.IMomentConverter;
import de.ansaru.happymoments.services.moments.converter.IMomentFileConverter;
import de.ansaru.happymoments.services.moments.converter.MomentConverter;
import de.ansaru.happymoments.services.moments.converter.MomentFileConverter;
import de.ansaru.happymoments.services.user.IOneTimePadService;
import de.ansaru.happymoments.services.user.IUserService;
import de.ansaru.happymoments.services.user.OneTimePadService;
import de.ansaru.happymoments.services.user.UserService;
import de.ansaru.happymoments.services.user.converter.IUserConverter;
import de.ansaru.happymoments.services.user.converter.UserConverter;
import de.ansaru.happymoments.services.user.utils.IOneTimePadUtils;
import de.ansaru.happymoments.services.user.utils.OneTimePadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
    public IAuthenticationService createAuthenticationService() {
        return new AuthenticationService();
    }

    @Bean
    public IMomentService createMomentService() {
        return new MomentService();
    }

    @Bean
    public IMomentFileService createMomentFileService() {
        return new MomentFileService();
    }

    @Bean
    public IOneTimePadService createOneTimePadService() { return new OneTimePadService(); }

    @Bean
    public IUserService createUserService() { return new UserService(); }

    @Bean
    public IUserDetailsConverter createHappyMomentsUserDetailsConverter() {
        return new UserDetailsConverter();
    }

    @Bean
    public IMomentDatabaseService createMomentDatabaseService() {
        return new MomentDatabaseService();
    }

    @Bean
    public IMomentFileDatabaseService createMomentFileDatabaseService() {
        return new MomentFileDatabaseService();
    }

    @Bean
    public IOneTimePadDatabaseService createOneTimePadDatabaseService() {
        return new OneTimePadDatabaseService();
    }

    @Bean
    public IUserDatabaseService createUserDatabaseService() {
        return new UserDatabaseService();
    }

    @Bean
    public IMomentConverter createMomentConverter() {
        return new MomentConverter();
    }

    @Bean
    public IMomentFileConverter createMomentFileConverter() {
        return new MomentFileConverter();
    }

    @Bean
    public IUserConverter createUserConverter() {
        return new UserConverter();
    }

    @Bean
    public IOneTimePadUtils createOneTimePadUtils() {
        return new OneTimePadUtils();
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
