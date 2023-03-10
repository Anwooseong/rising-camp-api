package com.example.demo.src.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseOrder {

    @NotNull
    private int orderId;

    @NotNull
    @Size(max = 200)
    private String address;

    @NotNull
    private int totalPrice;
}
