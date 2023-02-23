package com.example.demo.src.basket;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.basket.model.PostBasketReq;
import com.example.demo.src.basket.model.PostBasketRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BasketService {

    private final BasketDao basketDao;

    @Transactional
    public PostBasketRes postBasket(PostBasketReq postBasketReq) throws BaseException {
        int basketId = basketDao.postBasket(postBasketReq);
        return new PostBasketRes(basketId, postBasketReq.getUserId(), postBasketReq.getItemId(), postBasketReq.getItemCount());
    }

    @Transactional
    public String deleteBasket(int basketId) throws BaseException{
        int result = basketDao.deleteBasket(basketId);
        if (result == 0) {
            throw new BaseException(BaseResponseStatus.DELETE_FAIL_BASKET);
        }
        return "basketId : " + basketId + " 삭제 완료";
    }
}
