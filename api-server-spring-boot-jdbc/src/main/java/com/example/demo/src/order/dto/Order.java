package com.example.demo.src.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @NotNull
    @Positive
    private int orderId;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 200)
    private String address;

    @NotNull
    private String  orderDate;
}
