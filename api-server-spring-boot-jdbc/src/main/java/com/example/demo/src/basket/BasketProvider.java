package com.example.demo.src.basket;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.basket.model.GetBasketRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BasketProvider {

    private final BasketDao basketDao;

    public List<GetBasketRes> getBaskets(int userId) {
        List<GetBasketRes> getBasketRes = basketDao.getBaskets(userId);
        return getBasketRes;
    }
}

