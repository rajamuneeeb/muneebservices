package com.muneebcode.job.email;

import com.muneebcode.customer.Customer;
import com.muneebcode.customer.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class EmailSendingWriter implements ItemWriter<Customer> {
    private final CustomerRepository customerRepository;


    @Override
    public void write(@NonNull List<? extends Customer> list) throws Exception {
        log.info("Write Customer in Db list size {}" , list.size());
        customerRepository.saveAll(list);


    }
}
