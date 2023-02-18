package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.dto.BaseOrder;
import com.example.demo.src.order.dto.Order;
import com.example.demo.src.order.dto.OrderDetailItems;
import com.example.demo.src.order.model.GetOrderDetailsRes;
import com.example.demo.src.order.model.GetOrdersRes;
import com.example.demo.src.order.model.GetPaymentRes;
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

    public GetOrderDetailsRes getOrderDetails(int orderId) throws BaseException {
        try {
            BaseOrder baseOrder = orderDao.getBaseOrder(orderId);
            String arriveDate = orderDao.getArriveDate(orderId);
            List<OrderDetailItems> orderDetailItemsList = orderDao.getOrderDetailItems(orderId);
            return new GetOrderDetailsRes(baseOrder.getOrderId(), baseOrder.getAddress(), baseOrder.getTotalPrice(), arriveDate, orderDetailItemsList);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public GetPaymentRes getPayment(int paymentId) throws BaseException{
        try {
            GetPaymentRes getPaymentRes = orderDao.getPayment(paymentId);
            return getPaymentRes;
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
