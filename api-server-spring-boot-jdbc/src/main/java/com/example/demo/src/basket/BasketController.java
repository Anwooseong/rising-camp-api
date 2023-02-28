package com.example.demo.src.basket;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.basket.model.GetBasketRes;
import com.example.demo.src.basket.model.PostBasketReq;
import com.example.demo.src.basket.model.PostBasketRes;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketService basketService;
    private final BasketProvider basketProvider;

    @GetMapping("")
    public BaseResponse<List<GetBasketRes>> getBasket(@RequestParam("userId") int userId) {
        List<GetBasketRes> getBasketRes = basketProvider.getBaskets(userId);
        return new BaseResponse<>(getBasketRes);
    }

    @PostMapping("")
    public BaseResponse<PostBasketRes> postBasket(@Validated @RequestBody PostBasketReq postBasketReq) {
        PostBasketRes postBasketRes = basketService.postBasket(postBasketReq);
        return new BaseResponse<>(postBasketRes);
    }

    @DeleteMapping("{basketId}")
    public BaseResponse<String> deleteBasket(@PathVariable int basketId) {
        String result = basketService.deleteBasket(basketId);
        return new BaseResponse<>(result);
    }
}
