package com.example.boardlogin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {

    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    private String addr;
    private String createdAt;

}