package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.dto.BaseOrder;
import com.example.demo.src.order.dto.Order;
import com.example.demo.src.order.dto.OrderDetailItems;
import com.example.demo.src.order.model.GetOrderDetailsRes;
import com.example.demo.src.order.model.PostOrderReq;
import com.example.demo.src.order.model.PostOrderRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
    private OrderDao orderDao;

    @Transactional
    public PostOrderRes createOrder(PostOrderReq postOrderReq) throws BaseException {
        int insertId = 2;
        if (orderDao.createOrder(postOrderReq) == null){
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        }
        System.out.println(insertId);
        PostOrderRes postOrderRes = orderDao.findById(insertId);
        return postOrderRes;
    }
}
