package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.config.auth.OAuthAttributes;
import com.shoppingmall.shoppingmall.domain.Customer;
import com.shoppingmall.shoppingmall.domain.CustomerRepository;
import com.shoppingmall.shoppingmall.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    // loadUser 메서드: 카카오 로그인 성공 후, 사용자 정보를 가져왔을 때 호출되는 핵심 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 로그인 시 키가 되는 필드 값(PK)을 가져옵니다. (카카오는 "id")
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // OAuthAttributes 클래스를 사용해, 가져온 사용자 정보를 우리 규격에 맞게 변환합니다.
        OAuthAttributes attributes = OAuthAttributes.of(userNameAttributeName, oAuth2User.getAttributes());

        // 최종적으로, 스프링 시큐리티가 이해할 수 있는 형태의 사용자 정보를 반환합니다.
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.USER.getKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }
}