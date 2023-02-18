package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewDao reviewDao;

    public PostReviewRes createReview(PostReviewReq postReviewReq) throws BaseException {
        try {
            int reviewId = reviewDao.createReview(postReviewReq);
            return new PostReviewRes(reviewId, postReviewReq.getTitle(), postReviewReq.getComment());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void modifyReview(PatchReviewReq patchReviewReq) throws BaseException{
        try {
            int result = reviewDao.modifyTitleAndComment(patchReviewReq);
            if (result == 0) {
                throw new BaseException(BaseResponseStatus.MODIFY_FAIL_TITLE_AND_COMMENT);
            }
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
