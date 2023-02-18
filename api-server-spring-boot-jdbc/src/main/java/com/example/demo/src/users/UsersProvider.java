package com.example.demo.src.users;


import com.example.demo.config.BaseException;
import com.example.demo.src.users.model.GetUsersRes;
import com.example.demo.src.users.model.PostLoginsReq;
import com.example.demo.src.users.model.PostLoginsRes;
import com.example.demo.src.users.model.Users;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UsersProvider {

    private final UsersDao usersDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UsersProvider(UsersDao usersDao, JwtService jwtService) {
        this.usersDao = usersDao;
        this.jwtService = jwtService;
    }

    public List<GetUsersRes> getUsers() throws BaseException{
        try{
            List<GetUsersRes> getUsersRes = usersDao.getUsers();
            return getUsersRes;
        }
        catch (Exception exception) {
            logger.error("App - getUserRes Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetUsersRes> getUsersByEmail(String email) throws BaseException {
        try {
            List<GetUsersRes> getUsersRes = usersDao.getUsersByEmail(email);
            return getUsersRes;
        } catch (Exception exception) {
            logger.error("App - getUsersByEmail Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public GetUsersRes getUser(int userIdx) throws BaseException {
        try {
            GetUsersRes getUsersRes = usersDao.getUser(userIdx);
            return getUsersRes;
        } catch (Exception exception) {
            logger.error("App - getUser Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{
            return usersDao.checkEmail(email);
        } catch (Exception exception){
            logger.error("App - checkEmail Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostLoginsRes logIn(PostLoginsReq postLoginsReq) throws BaseException {
        try {
            Users user = usersDao.getPwd(postLoginsReq);

            String encryptPwd;
            try {
                encryptPwd = new SHA256().encrypt(postLoginsReq.getPassword());
            } catch (Exception exception) {
                logger.error("App - logIn Provider Encrypt Error", exception);
                throw new BaseException(PASSWORD_DECRYPTION_ERROR);
            }

            if(user.getPassword().equals(encryptPwd)){
                int userIdx = user.getUserIdx();
                String jwt = jwtService.createJwt(userIdx);
                return new PostLoginsRes(userIdx,jwt);
            }
            else{
                throw new BaseException(FAILED_TO_LOGIN);
            }
        } catch (Exception exception) {
            logger.error("App - logIn Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
