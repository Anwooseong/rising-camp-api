package com.example.demo.src.order;

import com.example.demo.src.order.dto.BaseOrder;
import com.example.demo.src.order.dto.Order;
import com.example.demo.src.order.dto.OrderDetailItems;
import com.example.demo.src.order.model.GetPaymentRes;
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

    public BaseOrder getBaseOrder(int orderId) {
        String getBaseOrderQuery = "select Orders.orderId, address, amount as totalPrice from Orders inner join Payment as P on P.orderId = Orders.orderId where Orders.orderId = ?";
        return this.jdbcTemplate.queryForObject(getBaseOrderQuery,
                (rs, rowNum) -> new BaseOrder(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                ), orderId);
    }

    public String getArriveDate(int orderId) {
        String getArriveDateQuery = "select arriveDate from Delivery where orderId = ?";
        return this.jdbcTemplate.queryForObject(getArriveDateQuery,
                (rs, rowNum) -> rs.getString("arriveDate"), orderId);
    }

    public GetPaymentRes getPayment(int paymentId) {
        String getPaymentQuery = "select paymentId, Payment.orderId, address, amount, paymentDate from Payment inner join Orders on Orders.orderId = Payment.orderId" +
                " where Payment.orderId = ?";
        return this.jdbcTemplate.queryForObject(getPaymentQuery,
                (rs,rowNum)-> new GetPaymentRes(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ),paymentId);
    }
}
