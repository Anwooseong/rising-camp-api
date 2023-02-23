package com.example.demo.src.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutItemRes {
    @NotNull
    private int itemId;
    @NotNull
    private String title;
    @NotNull
    private String comments;
    @NotNull
    private int price;

    @NotNull
    private int stock;

    @NotNull
    private String imageUrl;
    @NotNull
    private int companyId;
    @NotNull
    private int sellerId;
}
