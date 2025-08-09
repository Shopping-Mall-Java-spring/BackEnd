package com.shoppingmall.shoppingmall.commons.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.shoppingmall.commons.auth.OAuthAttributes;
import com.shoppingmall.shoppingmall.repository.CustomerRepository;
import com.shoppingmall.shoppingmall.commons.auth.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON 변환을 위한 객체

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        // 2. 이 속성 맵을 우리 OAuthAttributes 클래스를 사용해 파싱합니다.
        OAuthAttributes parsedAttributes = OAuthAttributes.of("id", attributes);
        // 3. 파싱된 DTO에서 이름과 이메일을 가져옵니다.
        String name = parsedAttributes.getName();
        String email = parsedAttributes.getEmail();

        boolean isExistingUser = customerRepository.findByEmail(email).isPresent();

        // 응답 헤더를 JSON 타입으로 설정
        response.setContentType("application/json;charset=UTF-8");

        if (isExistingUser) {
            // 기존 회원이면: JWT 토큰을 담은 JSON을 응답 바디에 직접 작성
            String token = jwtUtil.generateToken(email);

            Map<String, String> tokenResponse = new HashMap<>();
            tokenResponse.put("status", "success");
            tokenResponse.put("message", "로그인에 성공했습니다. 발급된 토큰은 아래와 같습니다.");
            tokenResponse.put("token", token);

            // JSON 문자열로 변환하여 응답에 쓰기
            response.getWriter().write(objectMapper.writeValueAsString(tokenResponse));

        } else {
            // 신규 회원이면: 추가 정보 입력을 안내하는 JSON을 응답 바디에 직접 작성
            Map<String, String> signupResponse = new HashMap<>();
            signupResponse.put("status", "new_user");
            signupResponse.put("message", "회원가입이 필요합니다. 아래 정보를 사용하여 /api/v1/signup 으로 POST 요청을 보내주세요.");
            signupResponse.put("name", name);
            signupResponse.put("email", email);

            response.getWriter().write(objectMapper.writeValueAsString(signupResponse));
        }
    }
}