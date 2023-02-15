package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;
    public int checkPhone(String phone) throws BaseException {
        try {
            return userDao.checkPhone(phone);
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
