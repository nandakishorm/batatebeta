package com.kishor.batatebeta.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Nandakishor on 9/18/2015.
 */
@Configuration
@PropertySource(value = {"mail.properties"})
public class EmailConfig {

    @Value("${email.host}")
    private String emailHost;

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.to}")
    private String emailTo;

    @Value("${email.subject}")
    private String emailSubject;

    @Value("${email.username}")
    private String emailUsername;

    @Value("${email.password}")
    private String emailPassword;

    @Value("${mail.transport.protocol}")
    private String emailProtocol;

    @Value("${email.port}")
    private Integer emailPort;

    @Value("${mail.smtp.auth}")
    private boolean smtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private boolean smtpStarttls;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", smtpStarttls);
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setHost(emailHost);
        javaMailSender.setPort(emailPort);         // Port number for gmail is 587, by default it's 25
        javaMailSender.setProtocol(emailProtocol); // By default it's always smtp
        javaMailSender.setUsername(emailUsername);
        javaMailSender.setPassword(emailPassword);
        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailFrom);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(emailSubject);
        return simpleMailMessage;
    }

    @Bean
    public MimeMessageHelper mimeMessageHelper() throws MessagingException {
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) javaMailSender();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(emailFrom);
        mimeMessageHelper.setTo(emailTo);
        mimeMessageHelper.setSubject(emailSubject);
        return mimeMessageHelper;
    }
}
