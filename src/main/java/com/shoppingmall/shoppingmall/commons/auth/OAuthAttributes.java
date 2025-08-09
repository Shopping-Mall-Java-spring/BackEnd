package com.shoppingmall.shoppingmall.commons.auth;

import com.shoppingmall.shoppingmall.domain.Customer;
import com.shoppingmall.shoppingmall.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    // 카카오에서 받아온 JSON 데이터를 파싱해서 DTO 형태로 변환
    public static OAuthAttributes of(String userNameAttributeName, Map<String, Object> attributes) {
        // 1. kakao_account에서 이메일 정보 추출
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        // 2. properties에서 닉네임 정보 추출
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        String nickname = (String) properties.get("nickname");

        return OAuthAttributes.builder()
                .name(nickname)
                .email(email)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // DTO에 담긴 정보를 바탕으로 Customer 엔티티를 생성하는 부분
    public Customer toEntity() {
        return Customer.builder()
                .name(name)
                .email(email)
                .address("소셜 로그인 주소 입력 필요")  // 필수 값이므로 임시값 설정
                .phone("소셜 로그인 번호 입력 필요")    // 필수 값이므로 임시값 설정
                .role(Role.USER)                   // 기본 권한은 USER로 설정
                .build();
    }
}