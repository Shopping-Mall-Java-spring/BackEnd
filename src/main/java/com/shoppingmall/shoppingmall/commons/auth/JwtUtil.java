package com.shoppingmall.shoppingmall.commons.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtUtil {

    // application.yml 파일에서 정의한 JWT 비밀 키를 가져옵니다.
    @Value("${jwt.secret}")
    private String secretKey;

    // 토큰 유효 기간
    private final long validityInHours = 24;

    public String generateToken(String email) {

        var key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        // 현재 시간과 만료 시간을 정의합니다.
        Instant now = Instant.now();
        Instant expiryTime = now.plus(validityInHours, ChronoUnit.HOURS);

        // JWT를 빌더 패턴을 사용해 생성합니다.
        return Jwts.builder()
                .setSubject(email) // 토큰의 주체로 사용자 이메일을 설정합니다.
                .setIssuer("shoppingmall") // 토큰 발급자
                .setIssuedAt(Date.from(now)) // 토큰 발급 시간
                .setExpiration(Date.from(expiryTime)) // 토큰 만료 시간
                .signWith(key) // 생성된 키로 서명합니다.
                .compact(); // 최종적으로 문자열 형태의 JWT를 생성합니다.
    }
}