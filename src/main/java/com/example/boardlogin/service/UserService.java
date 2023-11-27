package com.example.boardlogin.service;

import com.example.boardlogin.entity.UserEntity;
import com.example.boardlogin.repository.UserRepository;
import com.example.boardlogin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String loginProcess(UserVO user, HttpServletRequest request) {

        UserEntity userEntity = userRepository.findByUserId(user.getUserId());

        if (userEntity == null || !passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            return "아이디 또는 비밀번호를 확인해주세요.";
        }

        HttpSession session = request.getSession(true);

        session.setAttribute("userIdx", userEntity.getIdx());
        session.setAttribute("userId", userEntity.getUserId());
        session.setAttribute("name", userEntity.getName());

        return "로그인 성공";
    }

    public String logoutProcess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("[logoutProcess] session info : {} ", session);

        if (session != null) {
            session.invalidate();
            return "로그아웃 성공";
        }

        return "로그인 정보가 없습니다. 로그인 해주세요.";
    }

    public String signupUserIdCheck(UserVO userVO) {

        UserEntity user = userRepository.findByUserId(userVO.getUserId());

        if (user == null) {
            return "중복된 계정이 없습니다.";
        }

        return "사용중인 아이디입니다.";
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
            return "회원가입 실패";
        }

        return "회원가입 성공";
    }

}
