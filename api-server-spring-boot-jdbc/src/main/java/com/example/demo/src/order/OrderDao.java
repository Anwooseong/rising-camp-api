package com.example.demo.src.order;

import com.example.demo.src.order.dto.Order;
import com.example.demo.src.order.dto.OrderDetailItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Order> getOrders(int userId) {
        String getOrdersQuery = "select Orders.orderId, User.name, Orders.address, Orders.orderDate from Orders inner join User on User.userId = Orders.userId where Orders.userId = ?";
        return this.jdbcTemplate.query(getOrdersQuery,
                (rs, rowNum) -> new Order(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ), userId);
    }

    public List<OrderDetailItems> getOrderDetailItems(int orderId) {
        String getOrderDetailItems = "select Item.title, itemCount, itemAmount from OrderDetail inner join Item on Item.itemId = OrderDetail.itemId where orderId = ?";
        return this.jdbcTemplate.query(getOrderDetailItems,
                (rs, rowNum) -> new OrderDetailItems(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3)
                )
                , orderId);
    }
}
