package com.example.demo.src.order.model;

import com.example.demo.src.order.dto.OrderDetailItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderDetailsRes {
    private int orderId;
    private String address;
    private int totalPrice;
    private String arriveDate;
    private List<OrderDetailItems> orderDetailItems;
}
