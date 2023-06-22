package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        /*
            요청 - "/hello"
            응답
              - 뷰 이름 : hello
              - 모델 name : coco
         */
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk()) // status가 200인지 확인
                .andDo(print()) // 렌더링 결과 콘솔에서 확인
                .andExpect(view().name("hello")) // View name이 hello인지 확인
                .andExpect(model().attribute("name", is("coco"))); // model의 name 값이 coco인지 확인
    }

}