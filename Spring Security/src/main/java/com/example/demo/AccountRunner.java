package com.example.demo;

import com.example.demo.account.AccountRepository;
import com.example.demo.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * User 정보를 입력 하기 위해서 임시로 만든 클래스 원래 필요 없음
 */
@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    private AccountService accountService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        accountService.createAccountEntity("coco", "1234");
    }
}
