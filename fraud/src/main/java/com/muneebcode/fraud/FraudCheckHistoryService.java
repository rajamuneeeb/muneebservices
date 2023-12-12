package com.muneebcode.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Slf4j
@Service
@AllArgsConstructor
public class FraudCheckHistoryService {
    public final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public Boolean isFraudCustomer(Integer customerId) {
        log.info("FraudCheckService with customerId {}" , customerId);
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder().customerId(customerId).isFraud(false).createdAt(LocalDateTime.now()).build());
        return false;

    }
}
