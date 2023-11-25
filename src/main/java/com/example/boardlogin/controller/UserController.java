package com.example.boardlogin.controller;

import com.example.boardlogin.entity.UserEntity;
import com.example.boardlogin.service.UserService;
import com.example.boardlogin.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login/{id}")
    public ResponseVO<String> loginProcess(UserEntity user) {
        return new ResponseVO<String>(HttpStatus.OK, userService.loginProcess(user));
    }

    @PostMapping("/signup/{id}")
    public ResponseVO<String> signupProcess(UserEntity user) {
        return new ResponseVO<String>(HttpStatus.OK, userService.signupProcess(user));
    }

}
