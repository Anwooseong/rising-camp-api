package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Positive
    @NotNull
    private int userId;

    @Positive
    @NotNull
    private int itemId;

    @NotNull
    private String title;

    @NotNull
    private String comment;
}
