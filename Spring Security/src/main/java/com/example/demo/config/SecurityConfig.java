package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/hello").permitAll()  // "/", "/hello"는 모두 볼 수 있음
                .anyRequest().authenticated()                       // 나머지 모든 요청은  인증이 필요
                .and()
            .formLogin()                                            // form 로그인 사용
                .and()
            .httpBasic();                                           // http basic 사용
    }

    /**
     * Spring Security에서 권장하는 패스워드 인코딩 방법
     * 인코딩을 하지 않으면 오류 발생
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
