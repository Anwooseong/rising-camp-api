package com.example.demo.src.review;

import com.example.demo.src.review.model.CheckPatchReview;
import com.example.demo.src.review.model.GetDetailReviewRes;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.src.review.model.PostReviewReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReviewDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createReview(PostReviewReq postReviewReq) {
        String createReviewQuery = "insert into Review(title, comment, userId, itemId) VALUES (?, ?, ?, ?)";
        Object[] createReviewParams = new Object[]{postReviewReq.getTitle(), postReviewReq.getComment(), postReviewReq.getUserId(), postReviewReq.getItemId()};
        this.jdbcTemplate.update(createReviewQuery, createReviewParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public List<GetDetailReviewRes> getReviews(int itemId) {
        String getDetailReviewQuery = "select Item.title, Review.title, comment from Review inner join Item on Item.itemId = Review.itemId where Review.itemId = ?";
        Object[] getDetailReviewParams = new Object[]{itemId};
        return this.jdbcTemplate.query(getDetailReviewQuery,
                 (rs, rowNum) -> new GetDetailReviewRes(
                         rs.getString(1),
                         rs.getString(2),
                         rs.getString(3)
                 ), getDetailReviewParams);
    }

    public CheckPatchReview getReview(int reviewId) {
        String getReviewQuery = "select reviewId, title, comment from Review where reviewId = ?";
        return this.jdbcTemplate.queryForObject(getReviewQuery,
                (rs, rowNum) -> new CheckPatchReview(
                        rs.getInt("reviewId"),
                        rs.getString("title"),
                        rs.getString("comment")
                ), reviewId);
    }

    public int modifyTitleAndComment(PatchReviewReq patchReviewReq) {
        String modifyReviewQuery = "update Review set title=?, comment=? where reviewId=?";
        Object[] modifyReviewParams = new Object[]{patchReviewReq.getTitle(), patchReviewReq.getComment(), patchReviewReq.getReviewId()};
        return this.jdbcTemplate.update(modifyReviewQuery, modifyReviewParams);
    }

    public List<GetDetailReviewRes> getPagingReviews(int itemId, int page) {
        String getPagingReviewsQuery = "select Item.title, Review.title, comment from Review inner join Item on Item.itemId = Review.itemId where Review.itemId = ? limit 3 offset ?";
        int startOffset = (page - 1) * 3;
        Object[] getDetailPagingReviewParams = new Object[]{itemId, startOffset};
        return this.jdbcTemplate.query(getPagingReviewsQuery,
                (rs, rowNum) -> new GetDetailReviewRes(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                ), getDetailPagingReviewParams);
    }
}
