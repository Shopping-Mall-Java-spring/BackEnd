package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.domain.Customer;
import com.shoppingmall.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.shoppingmall.domain.enums.Role;
import com.shoppingmall.shoppingmall.dto.CustomerSignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Long signup(CustomerSignupRequestDto requestDto) {
        Customer newCustomer = Customer.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .address(requestDto.getAddress())
                .phone(requestDto.getPhone())
                .role(Role.USER) // 정식 USER로 가입
                .build();
        return customerRepository.save(newCustomer).getCustomerKey();
    }
}