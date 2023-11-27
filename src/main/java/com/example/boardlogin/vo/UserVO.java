package com.example.boardlogin.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserVO {

    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    private String addr;
    private LocalDateTime createdAt;

}
