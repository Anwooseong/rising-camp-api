package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.dto.BaseOrder;
import com.example.demo.src.order.dto.OrderDetailItems;
import com.example.demo.src.order.model.GetOrderDetailsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private OrderDao orderDao;


}
