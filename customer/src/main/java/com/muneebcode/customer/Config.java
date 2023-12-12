package com.muneebcode.customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Configuration
public class Config {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        log.info("Config the Rest Template for WebServices");
        return new RestTemplate();
    }
}
