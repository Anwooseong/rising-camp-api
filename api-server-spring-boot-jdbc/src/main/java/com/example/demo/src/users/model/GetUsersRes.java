package com.example.demo.src.users.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersRes {
    private int userIdx;
    private String userName;
    private String ID;
    private String email;
    private String password;
}
