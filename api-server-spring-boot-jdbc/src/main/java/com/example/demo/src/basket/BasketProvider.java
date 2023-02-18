package com.example.demo.src.basket;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.basket.model.GetBasketRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BasketProvider {

    private final BasketDao basketDao;

    public List<GetBasketRes> getBaskets(int userId) throws BaseException {
        try {
            List<GetBasketRes> getBasketRes = basketDao.getBaskets(userId);
            return getBasketRes;
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

