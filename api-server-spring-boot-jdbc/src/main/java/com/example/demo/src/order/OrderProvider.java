package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.dto.Order;
import com.example.demo.src.order.model.GetOrdersRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderProvider {
    private final OrderDao orderDao;

    public List<GetOrdersRes> getOrders(int userId) throws BaseException {
        try {
            List<GetOrdersRes> getOrdersResList = new ArrayList<>();

            List<Order> orders = orderDao.getOrders(userId);
            for (Order order : orders) {
                getOrdersResList.add(new GetOrdersRes(order.getOrderId(), order.getName(), order.getOrderDate(), orderDao.getOrderDetailItems(order.getOrderId())));
            }
            return getOrdersResList;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
