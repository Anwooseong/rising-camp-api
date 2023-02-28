package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostLoginRes;
import com.example.demo.src.user.model.User;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;

    public int checkPhone(String phone) {
        return userDao.checkPhone(phone);
    }

    public List<GetUserRes> getUsers() {
        List<GetUserRes> getUsersRes = userDao.getUsers();
        return getUsersRes;
    }

    public GetUserRes getUser(int userId) {
        GetUserRes getUserRes = userDao.getUser(userId);
        return getUserRes;
    }

    @Transactional
    public PostLoginRes login(PostLoginReq postLoginReq) {
        User user = userDao.getPwd(postLoginReq);
        String encryptPwd;
        try {
            encryptPwd = new SHA256().encrypt(postLoginReq.getPassword());
        } catch (Exception exception) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if (user.getPassword().equals(encryptPwd)) {
            int userId = user.getUserId();
            String jwt = jwtService.createJwt(userId);
            return new PostLoginRes(userId, jwt);
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }
    }

    public int checkUid(String uid) {
        return userDao.checkUid(uid);
    }
}














