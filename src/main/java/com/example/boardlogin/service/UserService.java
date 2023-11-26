package com.example.boardlogin.service;

import com.example.boardlogin.entity.UserEntity;
import com.example.boardlogin.repository.UserRepository;
import com.example.boardlogin.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String loginProcess(UserEntity user) {

        UserEntity userEntity = userRepository.findByUserId(user);

        if (userEntity == null || !passwordEncoder.matches(userEntity.getPassword(), user.getPassword())) {
            return "아이디 또는 비밀번호를 확인해주세요.";
        }

        return "success";
    }

    public String signupProcess(UserVO user) {

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(user.getUserId());
        String password = passwordEncoder.encode(user.getPassword());
        userEntity.setPassword(password);
        userEntity.setName(user.getName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setAddr(user.getAddr());

        UserEntity result = userRepository.save(userEntity);

        if (result == null) {
            return "fail";
        }

        return "success";
    }

}
