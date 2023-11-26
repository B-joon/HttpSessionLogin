package com.example.boardlogin.controller;

import com.example.boardlogin.service.UserService;
import com.example.boardlogin.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 개인 테스트 DB를 사용하고 싶다면 Replace.NONE 설정을 해여한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    UserController userController;

    private final static Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @DisplayName("api 테스트")
    @Test
    void name() throws Exception {

        UserVO userVO = new UserVO();
        userVO.setUserId("1");

        String content = objectMapper.writeValueAsString(userVO);

        mvc.perform(post("/api/v1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andDo(print())
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    log.info(response.getContentAsString());
                });
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
                .andExpect(status().isCreated());

    }
}