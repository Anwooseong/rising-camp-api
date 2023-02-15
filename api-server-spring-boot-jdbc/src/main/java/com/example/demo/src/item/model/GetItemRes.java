package com.example.demo.src.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetItemRes {
    private int itemId;
    private String title;
    private String comments;
    private int price;
    private int stock;
    private int companyId;
    private int sellerId;

}
