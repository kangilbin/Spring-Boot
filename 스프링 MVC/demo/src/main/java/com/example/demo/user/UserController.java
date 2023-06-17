package com.example.demo.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    // Srping Boot에서 제공해주는 기본 설정 파일에 의해 자동으로 스프링 MVC 개발을 할 수 있다.
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/users/create")
    public  User create(@RequestBody User user) {
        return user;
    }
}
