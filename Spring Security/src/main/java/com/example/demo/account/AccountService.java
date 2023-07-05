package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;


// user 정보를 관리하는 Service에 UserDetailsService를 구현하거나
// 새로운 클래스를 만들어 UserDetailsService구현 한다. 중요한건 UserDetailsService가 Bean에 등록 되야한다.
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 새로운 acount 저장
    public AccountEntity createAccountEntity(String username, String password) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(username);
        accountEntity.setPassword(passwordEncoder.encode(password)); // 패스워드 인코딩

        return accountRepository.save(accountEntity);
    }

    // 로그인 처리 시에 자동으로 호출된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
            로그인할 때 username, password를 입력하는데 입력받은 username을 가지고와 해당 username에
            해당하는 user 정보에서 password를 가져와 입력받은 password와 비교해 같으면 로그인 처리한다.
            다를 경우 예외를 던진다.
         */
        Optional<AccountEntity> byUsername = accountRepository.findByUsername(username);
        AccountEntity accountEntity = byUsername.orElseThrow(() -> new UsernameNotFoundException(username)); // 해당하는 데이터가 없으면 예외 처리

        /*
         * UserDetails을 return 해줘야 하지만 UserDetails은 Servcie의 User 정보
         * 즉, 현재 프로젝트에서는 AccountEntity의 인터페이스이다. 이 인터페이스의
         * 기본 정보를 Spring Security에서 User라는 클래스로 지원해준다.
         * new User(id, pw, auhority)
         */
        return new User(accountEntity.getUsername(), accountEntity.getPassword(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")); // 권한 세팅
    }
}
