package com.example.demo.src.users;



import com.example.demo.config.BaseException;
import com.example.demo.src.users.model.PatchUsersReq;
import com.example.demo.src.users.model.PostUsersReq;
import com.example.demo.src.users.model.PostUsersRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UsersService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UsersDao usersDao;
    private final UsersProvider usersProvider;
    private final JwtService jwtService;


    @Autowired
    public UsersService(UsersDao usersDao, UsersProvider usersProvider, JwtService jwtService) {
        this.usersDao = usersDao;
        this.usersProvider = usersProvider;
        this.jwtService = jwtService;

    }

    //POST
    public PostUsersRes createUser(PostUsersReq postUsersReq) throws BaseException {
        //중복
        if(usersProvider.checkEmail(postUsersReq.getEmail()) ==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUsersReq.getPassword());
            postUsersReq.setPassword(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int userIdx = usersDao.createUser(postUsersReq);
            //jwt 발급.
            String jwt = jwtService.createJwt(userIdx);
            return new PostUsersRes(jwt,userIdx);
        } catch (Exception exception) {
            logger.error("App - createUser Service Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserName(PatchUsersReq patchUsersReq) throws BaseException {
        try{
            int result = usersDao.modifyUserName(patchUsersReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            logger.error("App - modifyUserName Service Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
