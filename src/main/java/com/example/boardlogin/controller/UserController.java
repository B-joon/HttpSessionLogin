package com.example.boardlogin.controller;

import com.example.boardlogin.service.UserService;
import com.example.boardlogin.vo.ResponseVO;
import com.example.boardlogin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResponseVO<String> loginProcess(@RequestBody UserVO user, HttpServletRequest request) {
        return new ResponseVO<String>(HttpStatus.FOUND, userService.loginProcess(user, request));
    }

    @PostMapping("/user/logout")
    public ResponseVO<String> logoutProcess(HttpServletRequest request) {
        return new ResponseVO<>(HttpStatus.OK, userService.logoutProcess(request));
    }

    @PostMapping("/user/idcheck")
    public ResponseVO<String> signupUserIdCheck(UserVO userVO) {
        return new ResponseVO<>(HttpStatus.NOT_FOUND, userService.signupUserIdCheck(userVO));
    }

    @PostMapping("/user/signup")
    public ResponseVO<String> signupProcess(@RequestBody UserVO user) {
        return new ResponseVO<String>(HttpStatus.CREATED, userService.signupProcess(user));
    }

}
