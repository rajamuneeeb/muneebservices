package com.muneebcode.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckHistoryController {
    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping(path = "/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        log.info("Request come from Customer Application with customerId {}" , customerId);
        boolean isFraud = fraudCheckHistoryService.isFraudCustomer(customerId);
        return new FraudCheckResponse(isFraud);
    }

}
