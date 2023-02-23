package com.example.demo.src.item;

import com.example.demo.src.item.model.GetItemRes;
import com.example.demo.src.item.model.PatchItemReq;
import com.example.demo.src.item.model.PostItemReq;
import com.example.demo.src.item.model.PutItemRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Repository
public class ItemDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createItem(PostItemReq postItemReq) {
        String createItemQuery = "insert into Item (title, comments, price, stock, image, companyId, sellerId, createAt)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] createItemParams = new Object[]{postItemReq.getTitle(), postItemReq.getComments(), postItemReq.getPrice(), postItemReq.getStock(), postItemReq.getImageUrl(), postItemReq.getCompanyId(), postItemReq.getSellerId(), LocalDateTime.now()};
        this.jdbcTemplate.update(createItemQuery, createItemParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    public GetItemRes getItem(int itemId) {
        String getItemQuery = "select * from Item where itemId = ?";
        int getItemId = itemId;
        return this.jdbcTemplate.queryForObject(getItemQuery,
                (rs, rowNum) -> new GetItemRes(
                        rs.getInt("itemId"),
                        rs.getString("title"),
                        rs.getString("comments"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getInt("companyId"),
                        rs.getInt("sellerId")
                ), getItemId);
    }

    public int modifyPriceAndStock(PatchItemReq patchItemReq) {
        String modifyItemQuery = "update Item set price = ?, stock = ? where itemId = ?";
        Object[] modifyItemParams = new Object[]{patchItemReq.getPrice(), patchItemReq.getStock(), patchItemReq.getItemId()};

        return this.jdbcTemplate.update(modifyItemQuery, modifyItemParams);

    }

    public int deleteItem(int itemId) {
        String deleteItemQuery = "delete from Item where itemId = ?";
        return this.jdbcTemplate.update(deleteItemQuery, itemId);
    }

    public void changeItem(int itemId, PostItemReq item) {
        String changeItemQuery = "update Item set title =?, comments=?, price=?, stock=?, image=?, companyId=?, sellerId=?, createAt=? where itemId = ?";

        Object[] createItemParams = new Object[]{item.getTitle(), item.getComments(), item.getPrice(), item.getStock(), item.getImageUrl(), item.getCompanyId(), item.getSellerId(), LocalDateTime.now(), itemId};
        this.jdbcTemplate.update(changeItemQuery, createItemParams);
    }

    public PutItemRes findByItem(int itemId) {
        String checkItemQuery = "select itemId, title, comments, price, stock, image, companyId, sellerId from Item where itemId = ?";
        return this.jdbcTemplate.queryForObject(checkItemQuery,
                (rs, rowNum) -> new PutItemRes(
                        rs.getInt("itemId"),
                        rs.getString("title"),
                        rs.getString("comments"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("image"),
                        rs.getInt("companyId"),
                        rs.getInt("sellerId")
                )
                ,itemId);
    }
}
