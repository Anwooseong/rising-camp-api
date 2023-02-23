package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostUserReq {

    @NotNull
    @Size(max = 20)
    private String name;
    @NotNull
    @Size(max = 100)
    private String uid;
    @NotNull
    @Size(max = 100)
    private String password;
    @NotNull
    @Size(max = 20)
    private String phone;
    @NotNull
    @Size(max = 200)
    private String address;
}
