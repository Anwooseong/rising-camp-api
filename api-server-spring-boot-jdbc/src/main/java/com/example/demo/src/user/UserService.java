package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;

    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        if (userProvider.checkPhone(postUserReq.getPhone()) == 1) {
            throw new BaseException(POST_USERS_EXISTS_PHONE);
        }
        String pwd;
        try {
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try {
            int userId = userDao.createUser(postUserReq);
            String jwt = jwtService.createJwt(userId);
            return new PostUserRes(userId, postUserReq.getName(), jwt);
        }catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
