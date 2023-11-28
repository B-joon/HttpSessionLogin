package com.example.boardlogin.controller;

import com.example.boardlogin.service.UserService;
import com.example.boardlogin.vo.UserVO;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 개인 테스트 DB를 사용하고 싶다면 Replace.NONE 설정을 해여한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserService userService;

    @InjectMocks
    UserController userController;

    private final static Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @Nested
    @DisplayName("로그인")
    class login_test {

        @DisplayName("유저 정보 세팅")
        @BeforeEach
        void setUp() throws Exception{

            UserVO user = new UserVO();
            user.setUserId("admin");
            user.setPassword("admin12");
            user.setName("admin");
            user.setPhoneNumber("010-0000-0000");
            user.setAddr("Seoul");

            String content = new Gson().toJson(user);

            mvc.perform(post("/api/user/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)).andDo(print());

        }

        @DisplayName("로그인 테스트")
        @Test
        void select_user_test() throws Exception {

            UserVO user = new UserVO();
            user.setUserId("admin");
            user.setPassword("admin12");

            Gson gson = new Gson();
            String content = gson.toJson(user);

            mvc.perform(post("/api/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data", "로그인 성공").exists())
                    .andExpect(jsonPath("$.data").value("로그인 성공"))
                    .andReturn();

        }
    }

    @Nested
    @DisplayName("로그아웃")
    class logout_test {

        @DisplayName("유저 정보 세팅")
        @BeforeEach
        void setUp() throws Exception{

            UserVO user = new UserVO();
            user.setUserId("admin");
            user.setPassword("admin12");
            user.setName("admin");
            user.setPhoneNumber("010-0000-0000");
            user.setAddr("Seoul");

            String content = new Gson().toJson(user);

            mvc.perform(post("/api/user/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)).andDo(print());

            mvc.perform(post("/api/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)).andDo(print());

        }

        @DisplayName("로그아웃 테스트")
        @Test
        void out_session() throws Exception {

            mvc.perform(post("/api/user/logout"))
                    .andExpect(jsonPath("$.data", "로그아웃 성공").exists())
                    .andExpect(jsonPath("$.data").value("로그아웃 성공"))
                    .andReturn();

        }
    }

    @DisplayName("회원가입 계정 중복 확인")
    @Test
    void signup_userId_check() throws Exception{

        UserVO userVO = new UserVO();
        userVO.setUserId("admin");

        String content = new Gson().toJson(userVO);

        mvc.perform(post("api/user/idcheck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andDo(print())
                .andExpect(jsonPath("$.data", "중복된 계정이 없습니다.").exists())
                .andExpect(jsonPath("$.data").value("중복된 계정이 없습니다."))
                .andReturn();

    }

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
                .andExpect(jsonPath("$.data", "회원가입 성공").exists())
                .andExpect(jsonPath("$.data").value("회원가입 성공"))
                .andReturn();

    }
}