package com.example.demo.src.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailItems {

    @NotNull
    @Size(max = 300)
    private String title;

    @NotNull
    @Positive
    private int count;

    @NotNull
    @Positive
    private int itemAmount;
}
