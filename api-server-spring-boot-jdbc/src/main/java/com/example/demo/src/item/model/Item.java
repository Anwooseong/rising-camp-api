package com.example.demo.src.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @NotBlank
    @Size(max = 300)
    private String title;

    @NotNull
    private String comments;

    @PositiveOrZero
    private int price;

    @PositiveOrZero
    private int stock;

    @NotNull
    private String imageUrl;

    @NotNull
    private int companyId;

    @NotNull
    private int sellerId;
}
