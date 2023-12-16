package com.muneebcode.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Slf4j
@Service
@AllArgsConstructor
public class EmailService {
    private final EmailSender emailSender;

    public void sendEmailToRecipient(EmailRequestBody emailRequestBody) {
        log.info("Email service sending email to {} ", emailRequestBody.getRecipientEmail());
        try {

            emailSender.sendEmail(emailRequestBody.getRecipientEmail(), emailRequestBody.getSubject(), emailRequestBody.getContent());
            log.info("Email is send to {} ", emailRequestBody.getRecipientEmail());
        } catch (MessagingException e) {
            throw new RuntimeException("Messaging Exception");
        }


    }
}
