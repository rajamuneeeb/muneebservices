package com.muneebcode.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationDto customerRegistrationDto) {
        log.info("new Customer Registration request {}", customerRegistrationDto);
        customerService.registerCustomer(customerRegistrationDto);

    }

    @GetMapping("/check-customer/{customerId}")
    public Boolean checkCustomerById(@PathVariable Integer customerId) {
        return customerService.existById(customerId);

    }


}
