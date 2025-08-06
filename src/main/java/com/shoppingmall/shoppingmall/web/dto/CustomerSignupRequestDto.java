package com.shoppingmall.shoppingmall.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerSignupRequestDto {
    private String name;
    private String email;
    private String address;
    private String phone;
}