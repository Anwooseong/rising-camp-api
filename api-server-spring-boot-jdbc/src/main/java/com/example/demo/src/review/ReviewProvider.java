package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.review.model.CheckPatchReview;
import com.example.demo.src.review.model.GetDetailReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewProvider {
    private final ReviewDao reviewDao;
    public List<GetDetailReviewRes> getReviews(int itemId) {
        List<GetDetailReviewRes> getDetailReviewRes = reviewDao.getReviews(itemId);
        return getDetailReviewRes;
    }

    public CheckPatchReview getReview(int reviewId){
        CheckPatchReview getReview = reviewDao.getReview(reviewId);
        return getReview;
    }

    public List<GetDetailReviewRes> getPagingReviews(int itemId, int page) {
        List<GetDetailReviewRes> getPagingRes = reviewDao.getPagingReviews(itemId, page);
        return getPagingRes;
    }
}
