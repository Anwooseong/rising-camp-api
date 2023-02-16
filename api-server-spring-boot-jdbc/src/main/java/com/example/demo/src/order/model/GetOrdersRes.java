package com.example.demo.src.order.model;

import com.example.demo.src.order.dto.OrderDetailItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrdersRes {
    private int orderId;
    private String name;
    private String orderDate;
    private List<OrderDetailItems> orderDetailItems;

}
