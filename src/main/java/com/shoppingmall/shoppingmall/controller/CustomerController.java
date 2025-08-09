package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.commons.auth.JwtUtil;
import com.shoppingmall.shoppingmall.service.CustomerService;
import com.shoppingmall.shoppingmall.dto.CustomerSignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    @PostMapping("/api/v1/signup")
    public ResponseEntity<?> signup(@RequestBody CustomerSignupRequestDto requestDto) {
        customerService.signup(requestDto);
        String token = jwtUtil.generateToken(requestDto.getEmail());
        return ResponseEntity.ok().body(Collections.singletonMap("token", token));
    }
}