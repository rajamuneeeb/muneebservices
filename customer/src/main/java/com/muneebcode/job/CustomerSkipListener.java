package com.muneebcode.job;

import com.muneebcode.customer.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@JobScope
public class CustomerSkipListener implements SkipListener<Customer, Customer> {
    @Override
    public void onSkipInRead(Throwable throwable) {
        log.info("Customer is skipped in reading cause {} , message {} , stackTrace {}", throwable.getCause(), throwable.getMessage(), throwable.getStackTrace());

    }

    @Override
    public void onSkipInWrite(Customer customer, Throwable throwable) {
        log.info("Customer Skipper during writing customer {}", customer);
        log.info("Customer Skipper during writing customer cause {} , message {} , stackTrace {}", throwable.getCause(), throwable.getMessage(), throwable.getStackTrace());


    }

    @Override
    public void onSkipInProcess(Customer customer, Throwable throwable) {
        log.info("Customer skipped on processing customer {} ", customer);
        log.info("Customer skipped on processing cause {} , message {} , stackTrace {}", throwable.getCause(), throwable.getMessage(), throwable.getStackTrace());


    }
}
