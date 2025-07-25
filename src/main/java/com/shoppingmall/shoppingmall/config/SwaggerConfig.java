package com.shoppingmall.shoppingmall.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT"; // 보안 스키마의 이름 (Header Name이나 식별자 역할을 합니다.)

        // 1. SecurityRequirement 객체 생성: API 요청에 JWT 보안 스키마를 적용하겠다는 의미
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);

        // 2. Components 객체 생성: JWT 보안 스키마 정의
        //    - name: HTTP 헤더에서 사용할 이름 (보통 "Authorization")
        //    - type: HTTP (인증 방식)
        //    - scheme: bearer (JWT 인증에 사용되는 스킴)
        //    - bearerFormat: JWT (토큰의 형식)
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name("Authorization") // 실제 HTTP 헤더 이름 (일반적으로 "Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );

        // 3. OpenAPI 객체 생성 및 설정 적용
        return new OpenAPI()
                .info(apiInfo()) // API 기본 정보 설정 (제목, 설명, 버전)
                .addSecurityItem(securityRequirement) // API에 보안 요구사항 추가 (JWT 사용 명시)
                .components(components); // 정의된 보안 스키마 컴포넌트 추가
    }

    private Info apiInfo() {
        return new Info()
                .title("태연과 영서의 쇼핑몰 프로젝트") // API의 제목
                .description("Let's practice Swagger UI") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
