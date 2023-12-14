package com.muneebcode.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    public void saveNewOrder(OrderModel orderModel) {
        log.info("Order Service : orderModel {}", orderModel);
        orderRepository.save(orderModel);
        log.info("Order created successfully Order {} ", orderRepository.findById(orderModel.getOrderId()));
    }

    public Optional<OrderModel> getOrderById(Integer orderId) {
        log.info("Order Service Order id {} ", orderId);

        if (orderId != null) {
            return orderRepository.findById(orderId);
        }
        return Optional.empty();
    }

    public List<OrderModel> getAllOrder() {
        return orderRepository.findAll();
    }

    public void makeOrderOfCustomer(OrderDto orderDto) {
        log.info("Order service make customer order with OrderDto {} , customerId {} ", orderDto, orderDto.getCustomerId());
        Boolean responseFromCustomerApi = restTemplate.getForObject("http://CustomerApplication/api/v1/customers/check-customer/{customerId}", Boolean.class, orderDto.getCustomerId());
        log.info("Response from customer Api responseFromCustomerApi {} ", responseFromCustomerApi);
        if (Boolean.TRUE.equals(responseFromCustomerApi)) {
            log.info("Boolean check to save customer");
            OrderModel orderModel = new OrderModel.OrderModelBuilder().orderName(orderDto.getOrderName()).orderStatus(getOrderStatus(orderDto.getOrderStatus())).orderId(orderDto.getOrderId()).customerId(orderDto.getCustomerId()).build();
            orderRepository.save(orderModel);
            log.info("Save the order in db OrderModel {} ", orderRepository.findById(orderModel.getOrderId()));
        } else {
            throw new RuntimeException("Customer Not Found");
        }


    }

    private OrderModel.OrderStatus getOrderStatus(OrderDto.OrderStatus orderStatus) {
        if (orderStatus.name().equals(OrderModel.OrderStatus.PENDING.name())) {
            return OrderModel.OrderStatus.PENDING;
        }
        return OrderModel.OrderStatus.APPROVED;
    }
}
