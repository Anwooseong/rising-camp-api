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

    public CheckPatchReview getReview(int reviewId) throws BaseException{
        CheckPatchReview getReview = reviewDao.getReview(reviewId);
        return getReview;
    }
}
