package com.muneebcode.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orderData")
@Builder
public class OrderModel implements Serializable {
    @Id
    private Integer orderId;
    private String orderName;
    private OrderStatus orderStatus;
    private Integer customerId;

    public enum OrderStatus {
        PENDING, APPROVED
    }

}
