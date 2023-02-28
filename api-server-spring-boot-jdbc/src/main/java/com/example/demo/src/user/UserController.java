package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@Validated @RequestBody PostUserReq postUserReq){
//        if (postUserReq.getPhone() == null) {
//            return new BaseResponse<>(POST_USERS_EMPTY_SIGNUP);
//        }
        PostUserRes postUsersRes = userService.createUser(postUserReq);
        return new BaseResponse<>(postUsersRes);
    }

    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@Validated @RequestBody PostLoginReq postLoginReq) {
        PostLoginRes postLoginRes = userProvider.login(postLoginReq);
        return new BaseResponse<>(postLoginRes);
    }

    @GetMapping("")
    public BaseResponse<List<GetUserRes>> getUsers() {
        List<GetUserRes> getUserRes = userProvider.getUsers();
        return new BaseResponse<>(getUserRes);
    }

    @GetMapping("{userId}")
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        GetUserRes getUserRes = userProvider.getUser(userId);
        return new BaseResponse<>(getUserRes);

    }

    @PatchMapping("{userId}")
    public BaseResponse<GetUserRes> patchUser(@PathVariable("userId") int userId,@Validated @RequestBody User user) {
        int userIdByJwt = jwtService.getUserId();
        if(userId != userIdByJwt){
            return new BaseResponse<>(INVALID_USER_JWT);
        }
        PatchUserReq patchUserReq = new PatchUserReq(userId, user.getPhone(), user.getAddress());
        GetUserRes findUser = userService.modifyUser(patchUserReq);
        return new BaseResponse<>(findUser);
    }

    @DeleteMapping("{userId}")
    public BaseResponse<String> deleteUser(@PathVariable int userId){
        String result = userService.deleteUser(userId);
        return new BaseResponse<>(result);
    }
}
