package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello(Model model) { // 1) model은 화면에 전달할 데이터들을 담을 수 있다.

        // 2) View의 이름은 return 값의로 표현하기 때문에 ModelAndView를 사용하지 않고 Model을 사용한다.
        model.addAttribute("name", "coco");
        return "hello";
    }
}
