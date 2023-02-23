package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.PostReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewDao reviewDao;

    @Transactional
    public PostReviewRes createReview(PostReviewReq postReviewReq) throws BaseException {
        int reviewId = reviewDao.createReview(postReviewReq);
        return new PostReviewRes(reviewId, postReviewReq.getTitle(), postReviewReq.getComment());
    }

    @Transactional
    public void modifyReview(PatchReviewReq patchReviewReq) throws BaseException{
        int result = reviewDao.modifyTitleAndComment(patchReviewReq);
        if (result == 0) {
            throw new BaseException(BaseResponseStatus.MODIFY_FAIL_TITLE_AND_COMMENT);
        }
    }
}
