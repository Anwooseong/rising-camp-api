package com.example.demo.src.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostItemReq {

    @NotBlank
    private String title;

    @NotBlank
    private String comments;

    @NotNull
    @PositiveOrZero
    private int price;

    @NotNull
    @PositiveOrZero
    private int stock;

    @NotNull
    private String imageUrl;

    @NotNull
    private int companyId;

    @NotNull
    private int sellerId;

}
