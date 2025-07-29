package com.shoppingmall.shoppingmall.domain.enums;

import lombok.Getter;

@Getter
public enum DeliveryRequest {
    NONE("문 앞에 놓아주세요"),
    CALL_BEFORE_DELIVERY("배송 전 연락주세요"),
    SECRET_DELIVERY("경비실에 맡겨주세요"),
    OTHER("기타 요청"); // ERD에 없지만 일반적인 예시로 추가

    private final String displayName;

    DeliveryRequest(String displayName) {
        this.displayName = displayName;
    }
}
