package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    private int userId;

    @Size(max = 20)
    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 100)
    private String uid;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String phone;

    @NotBlank
    @Size(max = 200)
    private String address;
}
