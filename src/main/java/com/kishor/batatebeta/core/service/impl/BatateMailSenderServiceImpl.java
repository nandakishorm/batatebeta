package com.kishor.batatebeta.core.service.impl;

import com.kishor.batatebeta.core.service.BatateMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;

@Component
public class BatateMailSenderServiceImpl implements BatateMailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private MimeMessageHelper mimeMessageHelper;

    @Override
    public boolean sendMailCreateUser() {
        /*simpleMailMessage.setText("New user account was created successfully");
        simpleMailMessage.setSubject("Batate :: New user account creation");*/
        try {
            mimeMessageHelper.setSubject("Batate :: New user account creation");
            mimeMessageHelper.setText(
                    "<html><body>New user account was created successfully<br>" +
                        "<img src=''cid:happyFace''><br></body></html>", true);
            FileSystemResource res = new FileSystemResource(new File("d:/Sample.jpg"));
            mimeMessageHelper.addInline("happyFace", res);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
