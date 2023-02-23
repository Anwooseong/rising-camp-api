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
public class PostReviewReq {

    @NotNull
    @Positive
    private int userId;

    @NotNull
    @Positive
    private int itemId;

    @NotNull
    private String title;

    @NotNull
    private String comment;

}
