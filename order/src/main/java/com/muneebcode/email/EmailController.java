package com.muneebcode.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v2/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping
    public String test() {
        return "Muneeb Carry on";
    }

    @PostMapping("/send-test")
    public void sendEmail(@RequestBody EmailRequestBody emailRequestBody) {
        log.info("Request for sending email recipientEmail {} , subject {} , content {}", emailRequestBody.getRecipientEmail(), emailRequestBody.getSubject(), emailRequestBody.getContent());
        emailService.sendEmailToRecipient(emailRequestBody);
    }


}
