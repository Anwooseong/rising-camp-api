package com.example.demo.src.basket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBasketReq {

    @NotNull
    private int itemId;

    @NotNull
    private int userId;

    @NotNull
    @PositiveOrZero
    private int itemCount;
}
