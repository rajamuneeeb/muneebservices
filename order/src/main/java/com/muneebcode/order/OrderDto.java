package com.muneebcode.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
public class OrderDto implements Serializable {
    private Integer orderId;
    private String orderName;
    private OrderStatus orderStatus;
    private Integer customerId;

    public enum OrderStatus {
        PENDING, APPROVED
    }
}
