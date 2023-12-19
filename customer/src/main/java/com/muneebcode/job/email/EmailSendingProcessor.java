package com.muneebcode.job.email;

import com.muneebcode.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EmailSendingProcessor implements ItemProcessor<Customer, Customer> {
    private final EmailSender emailSender;

    @Override
    public Customer process(Customer customer) throws Exception {
        log.info("Process Customer {}" , customer);
        String content = emailSender.generateShortLeaveEmail(customer.getFirstName() + " " + customer.getLastName());
        log.info("Content for email :: content {}", content);
        try {

            emailSender.sendEmail(customer.getEmail(), "Leave For One Day", content);
            customer.setEmailSent(1);
        } catch (Exception e) {
            log.error("Error Occurred in sending email", e);
        }
        return customer;


    }
}
