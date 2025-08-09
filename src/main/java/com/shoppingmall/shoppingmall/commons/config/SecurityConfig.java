package com.shoppingmall.shoppingmall.commons.config;

import com.shoppingmall.shoppingmall.commons.handler.OAuth2LoginSuccessHandler;
import com.shoppingmall.shoppingmall.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        // 아래에 명시된 주소들은 인증 없이 누구나 접근할 수 있도록 허용합니다.
                        .requestMatchers(
                                "/",
                                "/error",
                                "/swagger-ui/**", // Swagger UI 페이지
                                "/v3/api-docs/**",  // Swagger API 문서
                                "/oauth2/**",       // OAuth2 로그인 과정
                                "/login/**",        // 로그인 관련 주소
                                "/auth/login/**",   // 엔드 포인트 주소
                                "/api/v1/signup",    // 최종 회원가입 API
                                "/products", // 전체 상품 조회 API
                                "/categories/**" // 카테고리별 상품 조회 API
                        ).permitAll()
                        // 위에서 허용한 주소 외의 모든 요청은 반드시 인증을 거쳐야 합니다.
                        .anyRequest().authenticated()
                )

                // OAuth2 소셜 로그인 설정을 시작합니다.
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/auth/login")
                        )
                        .successHandler(oAuth2LoginSuccessHandler)
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                );
        return http.build();
    }
}