package com.tsd.armsystem.service;

import com.tsd.armsystem.exception.MailException;
import com.tsd.armsystem.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;


    @Async
    public void sendMail(NotificationEmail notificationEmail){

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("resroucemanagementtsd@gmail.com");
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };

        try{
            javaMailSender.send(mimeMessagePreparator);
//            log.info("Activation email sent!");

        }catch (MailException e){
            e.printStackTrace();
            // throw new EmailException("Exception occurred when sending email to "+notificationEmail.getRecipient());
        }

    }
}
