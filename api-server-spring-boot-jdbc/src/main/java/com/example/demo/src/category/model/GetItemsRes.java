package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetItemsRes {

    private int itemId;
    private String categoryName;
    private String title;
    private String comments;
    private String image;
    private int price;

}
