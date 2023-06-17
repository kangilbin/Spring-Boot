package com.example.demo.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class) // 슬라이싱 테스트로 웹 계층만 테스트한다.
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc; // @WebMvctest에 의해 자동으로 Bean에 등록해줘 바로 사용할 수 있다.

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

    }

    @Test
    public void createUser_JSON() throws Exception {
        String userJSON = "{\"username\":\"coco\", \"password\":\"123\"}";
        mockMvc.perform(post("/users/create")
                    // 1) 요청 Type
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    // 2) 응답 Type
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    // 3) 본문 내용
                    .content(userJSON))
                // 5) 상태 코드가 200 (OK)인지 확인
                .andExpect(status().isOk())
                // 6) Json 본문에 username이 coco 인지 확인
                .andExpect(jsonPath("$.username", is(equalTo("coco"))))
                // 7) Json 본문에 password 123 인지 확인
                .andExpect(jsonPath("$.password", is(equalTo("123"))));
    }
}
