package com.example.boardlogin.controller;

import com.example.boardlogin.config.JpaConfig;
import com.example.boardlogin.service.UserService;
import com.example.boardlogin.vo.UserVO;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@Import(JpaConfig.class)
// 개인 테스트 DB를 사용하고 싶다면 Replace.NONE 설정을 해여한다.
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @InjectMocks
    UserController userController;



    @DisplayName("회원가입 테스트")
    @Test
    void insert_user_test() throws Exception {

        UserVO userVO = new UserVO();
        userVO.setUserId("admin");
        userVO.setPassword("admin12");
        userVO.setName("admin");
        userVO.setPhoneNumber("010-0000-0000");
        userVO.setAddr("Seoul");

        Gson gson = new Gson();
        String content = gson.toJson(userVO);

        mvc.perform(post("/api/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());

    }
}