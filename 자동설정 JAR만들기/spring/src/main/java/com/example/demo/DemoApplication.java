package com.example.demo;

import org.example.Cocococo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);

//        @Bean
//        public Cocococo cocococo() {
//            Cocococo cocococo = new Cocococo();
//            cocococo.setName("셋팅합니다.");
//            cocococo.setType("타입입니다.");
//
//            return cocococo;
//        }
    }

}
