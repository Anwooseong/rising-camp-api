package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentRes {
    private int paymentId;
    private int orderId;
    private String address;
    private int totalPrice;
    private String paymentDate;
}
