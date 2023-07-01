package com.example.demo.persistence;

import com.example.demo.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;


    @Test
    public void id() throws SQLException {
        Account account = new Account();
        account.setUsername("coco");
        account.setPassword("pass");

        Account newAccount = accountRepository.save(account);
        assertThat(newAccount).isNotNull(); // 등록된 account 확인

        Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        assertThat(existingAccount).isNotNull();    // username으로 가져온 account 확인

        Account nonExistingAccount = accountRepository.findByUsername("baba");
        assertThat(nonExistingAccount).isNull(); // 존재하지 않은 account 확인
    }

}