package com.shoppingmall.shoppingmall.domain.enums;

import lombok.Getter;

@Getter // Lombok 어노테이션: getter 메서드를 자동으로 생성
public enum PaymentMethod {
    CARD("카드"),
    CASH("현금"),
    BANK_TRANSFER("계좌이체"); // ERD에 없지만 일반적인 예시로 추가

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    // fromDisplayName 등의 편의 메서드는 필요에 따라 추가
}
