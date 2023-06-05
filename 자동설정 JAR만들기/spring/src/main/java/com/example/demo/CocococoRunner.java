package com.example.demo;

import org.example.Cocococo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CocococoRunner implements ApplicationRunner {
// Spring Boot 애플리케이션이 만들어지고 자동으로 실행되는 Bean 만들고 싶을 때 사용

    @Autowired
    Cocococo cocococo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(cocococo);
    }
}
