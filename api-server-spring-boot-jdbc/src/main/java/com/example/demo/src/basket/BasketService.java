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

    public String deleteBasket(int basketId) throws BaseException{
        try {
            int result = basketDao.deleteBasket(basketId);
            if (result == 0) {
                throw new BaseException(BaseResponseStatus.DELETE_FAIL_BASKET);
            }
            return "basketId : " + basketId + " 삭제 완료";
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}
