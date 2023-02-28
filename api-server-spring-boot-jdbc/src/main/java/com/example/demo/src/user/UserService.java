package com.example.demo.src.user;

import com.example.demo.GlobalExceptionHandler;
import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;

    public PostUserRes createUser(PostUserReq postUserReq) {
        if (userProvider.checkUid(postUserReq.getUid()) == 1) {
            throw new BaseException(POST_USERS_EXISTS_UID);
        }
        else if (userProvider.checkPhone(postUserReq.getPhone()) == 1) {
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
        int userId = userDao.createUser(postUserReq);
        String jwt = jwtService.createJwt(userId);
        return new PostUserRes(userId, postUserReq.getName(), jwt);
    }

    public GetUserRes modifyUser(PatchUserReq patchUserReq) {
        int result = userDao.modifyUserPhoneAndAddress(patchUserReq);
        if(result == 0){
            throw new BaseException(MODIFY_FAIL_PHONE_AND_ADDRESS);
        }
        GetUserRes user = userProvider.getUser(patchUserReq.getUserId());
        return user;
    }

    public String deleteUser(int userId) {
        int result = userDao.deleteUser(userId);
        if(result == 1){
            return "userId : " + userId + " 삭제 완료";
        }
        else {
            throw new BaseException(DELETE_FAIL_USER);
        }

    }
}
