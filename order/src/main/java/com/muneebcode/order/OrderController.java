package com.muneebcode.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addOrder(@RequestBody OrderModel orderModel) {
        log.info("Order created order {} ", orderModel);
        if (orderModel != null) {
            log.info("Setting Default Order Status Pending");
            orderModel.setOrderStatus(OrderModel.OrderStatus.PENDING);
            orderService.saveNewOrder(orderModel);
        }
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<OrderModel> getOrder(@PathVariable Integer orderId) {
        log.info("Get Order orderId {} ", orderId);
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderModel> getAllOrders() {
        return orderService.getAllOrder();
    }

    @PostMapping("/make-order")
    public void makeOrder(@RequestBody OrderDto orderDto) {
        orderService.makeOrderOfCustomer(orderDto);

    }



}
