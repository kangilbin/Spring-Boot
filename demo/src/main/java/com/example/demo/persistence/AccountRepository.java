package com.example.demo.persistence;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository<Entity타입, 식별자 타입>
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);


    // NativeQuery 사용 방법
    @Query(nativeQuery = true, value = "select * from account where username='{0}'")
    Account nativeQuety(String username);
}
