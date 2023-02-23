package com.example.demo.src.category;

import com.example.demo.src.category.model.GetItemsRes;
import com.example.demo.src.category.model.PostCategoryReq;
import com.example.demo.src.category.model.PostCategoryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDao {
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetItemsRes> getItems(int categoryId) {
        String getItemsQuery = "select Item.itemId, Categories.categoryName, Item.title, Item.comments, Item.image, Item.price from Categories inner join ItemCategory on ItemCategory.categoryId = Categories.categoryId" +
                " inner join Item on ItemCategory.itemId = Item.itemId where ItemCategory.categoryId = ?";
        return this.jdbcTemplate.query(getItemsQuery,
                (rs, rowNum) -> new GetItemsRes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                ), categoryId);
    }

    public int findCategory(PostCategoryReq postCategoryReq) {
        String findCategoryQuery = "select exists(select categoryId from Categories where parentCategoryId = ? and categoryName = ?)";
        Object[] findCategoryParams = new Object[]{postCategoryReq.getParentCategoryId(), postCategoryReq.getCategoryName()};
        return this.jdbcTemplate.queryForObject(findCategoryQuery, int.class, findCategoryParams);
    }

    public void createCategory(PostCategoryReq postCategoryReq) {
        String createCategoryQuery = "insert into Categories(categoryName, parentCategoryId) values (?, ?)";
        Object[] findCategoryParams = new Object[]{postCategoryReq.getCategoryName(), postCategoryReq.getParentCategoryId()};
        this.jdbcTemplate.update(createCategoryQuery, findCategoryParams);
    }

    public PostCategoryRes findByReq(PostCategoryReq postCategoryReq) {
        String findByReqQuery = "select categoryId, categoryName, parentCategoryId from Categories where parentCategoryId = ? and categoryName = ?";
        Object[] findCategoryParams = new Object[]{postCategoryReq.getParentCategoryId(), postCategoryReq.getCategoryName()};
        return this.jdbcTemplate.queryForObject(findByReqQuery,
                (rs, rowNum)-> new PostCategoryRes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                )
                ,findCategoryParams);
    }
}
