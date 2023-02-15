package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewProvider reviewProvider;
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public BaseResponse<PostReviewRes> createReview(@RequestBody PostReviewReq postReviewReq) {
        try {
            PostReviewRes postReviewRes = reviewService.createReview(postReviewReq);
            return new BaseResponse<>(postReviewRes);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    @GetMapping("/items/{itemId}/reviews")
    public BaseResponse<List<GetDetailReviewRes>> getReviews(@PathVariable int itemId) {
        try {
            List<GetDetailReviewRes> getDetailReviewRes = reviewProvider.getReviews(itemId);
            return new BaseResponse<>(getDetailReviewRes);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("/reviews/{reviewId}")
    public BaseResponse<CheckPatchReview> patchReview(@PathVariable int reviewId, @RequestBody Review review){
        try {
            PatchReviewReq patchReviewReq = new PatchReviewReq(reviewId, review.getTitle(), review.getComment());
            reviewService.modifyReview(patchReviewReq);
            CheckPatchReview getReview = reviewProvider.getReview(reviewId);
            return new BaseResponse<>(getReview);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
