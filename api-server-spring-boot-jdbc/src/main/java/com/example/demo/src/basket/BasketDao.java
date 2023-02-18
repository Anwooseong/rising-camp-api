package com.example.demo.src.basket;

import com.example.demo.src.basket.model.GetBasketRes;
import com.example.demo.src.basket.model.PostBasketReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BasketDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetBasketRes> getBaskets(int userId) {
        String getBasketsQuery = "select basketId, User.name, Item.title, Basket.itemCount from Basket inner join User on User.userId = Basket.userId" +
                " inner join Item on Item.itemId = Basket.itemId where Basket.userId = ?";
        return this.jdbcTemplate.query(getBasketsQuery,
                (rs, rowNum) -> new GetBasketRes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                )
                , userId);
    }

    public int postBasket(PostBasketReq postBasketReq) {
        String postBasketQuery = "insert into Basket (itemId, userId, itemCount)" +
                " VALUES (?, ?, ?)";
        Object[] postBasketParams = new Object[]{postBasketReq.getItemId(), postBasketReq.getUserId(), postBasketReq.getItemCount()};
        this.jdbcTemplate.update(postBasketQuery, postBasketParams);
        String lastIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastIdQuery, int.class);
    }

    public int deleteBasket(int basketId) {
        String deleteBasketQuery = "delete from Basket where basketId = ?";
        return this.jdbcTemplate.update(deleteBasketQuery, basketId);
    }
}
