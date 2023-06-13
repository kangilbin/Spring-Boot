package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    BlogProperties blogProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=================");
        System.out.println(blogProperties.getName());
        System.out.println(blogProperties.getAge());

        System.out.println(blogProperties.getDbName());
        System.out.println(blogProperties.getDbPort());
        System.out.println("=================");
    }
}
