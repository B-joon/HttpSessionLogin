package com.example.boardlogin.service;

import com.example.boardlogin.entity.UserEntity;
import com.example.boardlogin.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DisplayName("로그인 및 회원가입 테스트")
@DataJpaTest
// 개인 테스트 DB를 사용하고 싶다면 Replace.NONE 설정을 해여한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @DisplayName("로그인 테스트")
    @Test
    void loginTest() {

    }

    @DisplayName("회원가입 테스트")
    @Test
    void signupTest() {
        // given
        UserEntity user = new UserEntity();
        user.setUserId("admin");
        String password = passwordEncoder.encode("admin12");
        user.setPassword(password);
        user.setName("admin");
        user.setPhoneNumber("010-0000-0000");
        user.setAddr("어딘가");

        // when
        UserEntity result = userRepository.save(user);

        // then
        assertThat(user.getUserId()).isEqualTo(result.getUserId());

    }
}