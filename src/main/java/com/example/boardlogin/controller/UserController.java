package com.example.boardlogin.controller;

import com.example.boardlogin.entity.UserEntity;
import com.example.boardlogin.service.UserService;
import com.example.boardlogin.vo.ResponseVO;
import com.example.boardlogin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/v1")
    public ResponseVO<String> test(@RequestBody UserVO user) {
        String userId = user.getUserId();
        return new ResponseVO<String>(HttpStatus.OK, userId);
    }

    @PostMapping("/user/login")
    public ResponseVO<String> loginProcess(@RequestBody UserVO user) {
        return new ResponseVO<String>(HttpStatus.OK, userService.loginProcess(user));
    }

    @PostMapping("/user/signup")
    public ResponseVO<String> signupProcess(@RequestBody UserVO user) {
        return new ResponseVO<String>(HttpStatus.CREATED, userService.signupProcess(user));
    }

}
