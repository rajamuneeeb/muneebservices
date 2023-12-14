package com.muneebcode.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationDto customerRegistrationDto) {
        log.info("CusterService Req For Registration {}", customerRegistrationDto);
        Customer customer = new Customer.CustomerBuilder().firstName(customerRegistrationDto.getFirstName()).lastName(customerRegistrationDto.getLastName()).email(customerRegistrationDto.getEmail()).build();
        customerRepository.saveAndFlush(customer);

        log.info("Make Rest RestTemplate Call to Fraud Application url {} , customerId {}", "http://FraudApplication/api/v1/fraud-check/{customerId}", customer.getId());
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FraudApplication/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
        log.info("Response come from Fraud Application {}", fraudCheckResponse);
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) throw new IllegalStateException("fraudster");

    }

    public Boolean existById(Integer customerId) {
        return customerRepository.existsById(customerId);
    }
}
