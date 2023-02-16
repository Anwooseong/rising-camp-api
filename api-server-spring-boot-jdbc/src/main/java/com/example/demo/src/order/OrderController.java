package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.GetOrdersRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderProvider orderProvider;
    private final OrderService orderService;

    @GetMapping("/orders/{userId}")
    public BaseResponse<List<GetOrdersRes>> getOrders(@PathVariable int userId) {
        try {
            List<GetOrdersRes> getOrdersResList = orderProvider.getOrders(userId);
            return new BaseResponse<>(getOrdersResList);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }
}
