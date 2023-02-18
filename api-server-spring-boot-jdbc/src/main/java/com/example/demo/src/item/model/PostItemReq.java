package com.example.demo.src.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostItemReq {
    private String title;
    private String comments;
    private int price;
    private int stock;
    private String imageUrl;
    private int companyId;
    private int sellerId;

}
