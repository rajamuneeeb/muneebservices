package com.muneebcode.job;

import com.muneebcode.customer.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(@NonNull Customer customer) throws Exception {
        log.info("Customer Processed in item Proessor : customer {}", customer);
        return customer;
    }
}
