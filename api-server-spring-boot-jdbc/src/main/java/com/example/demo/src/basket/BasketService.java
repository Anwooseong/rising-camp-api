package com.example.demo.src.basket;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.basket.model.PostBasketReq;
import com.example.demo.src.basket.model.PostBasketRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BasketService {

    private final BasketDao basketDao;
    public PostBasketRes postBasket(PostBasketReq postBasketReq) throws BaseException {
        try {
            int basketId = basketDao.postBasket(postBasketReq);
            return new PostBasketRes(basketId, postBasketReq.getUserId(), postBasketReq.getItemId(), postBasketReq.getItemCount());
        }catch (Exception e){
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }
}
