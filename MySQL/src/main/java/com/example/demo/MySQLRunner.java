package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySQLRunner implements ApplicationRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String createTableQuery = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))";
        jdbcTemplate.execute(createTableQuery);

        jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'COCO')");
    }
}
