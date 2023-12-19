package com.muneebcode.job.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.context.Context;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@AllArgsConstructor
@Service
@Getter
public class EmailSender {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String email, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("rajamuneeb127@gmail.com");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);

        mailSender.send(mimeMessage);
        log.info("Email sent successfully to '{}'", email);

    }

    public String generateShortLeaveEmail(String name) {
        log.info("Generate short leave : {} ", name);
        log.info("Generate short leave: {}", name);

        // Create a Thymeleaf context
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("name", name);

        // Process the Thymeleaf template
        String processedTemplate = templateEngine.process("short-leave-template", thymeleafContext);

        log.info("Thymeleaf processing completed.");

        return processedTemplate;
    }
}

