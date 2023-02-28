package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewProvider reviewProvider;
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public BaseResponse<PostReviewRes> createReview(@Validated @RequestBody PostReviewReq postReviewReq) {
        PostReviewRes postReviewRes = reviewService.createReview(postReviewReq);
        return new BaseResponse<>(postReviewRes);
    }

    @GetMapping("/items/{itemId}/reviews")
    public BaseResponse<List<GetDetailReviewRes>> getReviews(@PathVariable int itemId) {
        List<GetDetailReviewRes> getDetailReviewRes = reviewProvider.getReviews(itemId);
        return new BaseResponse<>(getDetailReviewRes);
    }

    @PatchMapping("/reviews/{reviewId}")
    public BaseResponse<CheckPatchReview> patchReview(@PathVariable int reviewId, @Validated @RequestBody Review review) {
        PatchReviewReq patchReviewReq = new PatchReviewReq(reviewId, review.getTitle(), review.getComment());
        reviewService.modifyReview(patchReviewReq);
        CheckPatchReview getReview = reviewProvider.getReview(reviewId);
        return new BaseResponse<>(getReview);
    }

    @GetMapping("/reviews")
    public BaseResponse<List<GetDetailReviewRes>> getPagingReviews(@RequestParam("itemId") int itemId, @RequestParam("page") int page) {
        List<GetDetailReviewRes> getDetailReviewRes = reviewProvider.getPagingReviews(itemId, page);
        return new BaseResponse<>(getDetailReviewRes);
    }
}
