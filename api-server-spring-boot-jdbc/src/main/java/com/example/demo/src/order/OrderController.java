package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderProvider orderProvider;
    private final OrderService orderService;

    @GetMapping("/orders/{userId}")
    public BaseResponse<List<GetOrdersRes>> getOrders(@PathVariable int userId) {
        List<GetOrdersRes> getOrdersResList = orderProvider.getOrders(userId);
        return new BaseResponse<>(getOrdersResList);
    }

    @GetMapping("/order-details/{orderId}")
    public BaseResponse<GetOrderDetailsRes> getOrderDetails(@PathVariable int orderId) {
        GetOrderDetailsRes getOrderDetails = orderProvider.getOrderDetails(orderId);
        return new BaseResponse<>(getOrderDetails);
    }

    @GetMapping("/payments/{paymentId}")
    public BaseResponse<GetPaymentRes> getPaymentRes(@PathVariable int paymentId) {
        GetPaymentRes getPaymentRes = orderProvider.getPayment(paymentId);
        return new BaseResponse<>(getPaymentRes);
    }

    @PostMapping("/orders")
    public BaseResponse<PostOrderRes> createOrder(@Validated @RequestBody PostOrderReq postOrderReq)  {
        PostOrderRes postOrderRes = orderService.createOrder(postOrderReq);
        return new BaseResponse<>(postOrderRes);
    }
}
