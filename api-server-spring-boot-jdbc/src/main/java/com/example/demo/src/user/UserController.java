package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.POST_USERS_EMPTY_PHONE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        if (postUserReq.getPhone() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_PHONE);
        }
        try {
            PostUserRes postUsersRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUsersRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("")
    public BaseResponse<List<GetUserRes>> getUsers() {
        try {
            List<GetUserRes> getUserRes = userProvider.getUsers();
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("{userId}")
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        try {
            GetUserRes getUserRes = userProvider.getUser(userId);
            return new BaseResponse<>(getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PatchMapping("{userId}")
    public BaseResponse<GetUserRes> patchUser(@PathVariable("userId") int userId, @RequestBody User user){
        try{
            PatchUserReq patchUserReq = new PatchUserReq(userId, user.getPhone(), user.getAddress());
            userService.modifyUser(patchUserReq);
            GetUserRes findUser = userProvider.getUser(userId);
            return new BaseResponse<>(findUser);
        }catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @DeleteMapping("{userId}")
    public BaseResponse<String> deleteUser(@PathVariable int userId){
        try {
            String result = userService.deleteUser(userId);
            return new BaseResponse<>(result);
        }catch (BaseException e){
            return new BaseResponse<>((e.getStatus()));
        }
    }
}
