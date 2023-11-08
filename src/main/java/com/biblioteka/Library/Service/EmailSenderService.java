package com.biblioteka.Library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService{

    private final JavaMailSender mailSender;
    private final static String EMAIL= "lmajcher02@gmail.com";

    @Autowired
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String subject, String text, String toEmail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EMAIL);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setTo(toEmail);
        mailSender.send(mailMessage);
    }

}
