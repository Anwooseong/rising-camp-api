package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.review.model.CheckPatchReview;
import com.example.demo.src.review.model.GetDetailReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewProvider {
    private final ReviewDao reviewDao;
    public List<GetDetailReviewRes> getReviews(int itemId) throws BaseException {
        try {
            List<GetDetailReviewRes> getDetailReviewRes = reviewDao.getReviews(itemId);
            return getDetailReviewRes;
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public CheckPatchReview getReview(int reviewId) throws BaseException{
        try {
            CheckPatchReview getReview = reviewDao.getReview(reviewId);
            return getReview;
        }catch (Exception exception){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
