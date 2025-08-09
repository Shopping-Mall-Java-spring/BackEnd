package com.shoppingmall.shoppingmall.repository;

import com.shoppingmall.shoppingmall.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository<관리할 엔티티, 엔티티의 PK 타입>
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 이메일을 통해 이미 가입된 회원인지 처음인지 확인하기 위한 메서드
    Optional<Customer> findByEmail(String email);
}