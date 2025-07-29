package com.shoppingmall.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// (cartKey, itemKey) 조합의 유니크 제약 조건을 엔티티 레벨에 추가
@IdClass(CartItem.CartItemPk.class) // 복합 기본 키 사용 명시
public class CartItem {

    // Cart와의 ManyToOne 관계
    @Id // 복합 기본 키의 한 부분
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartItemkey", nullable = false) // 외래 키 컬럼 명시
    private Cart cart;

    // Item과의 ManyToOne 관계
    @Id // 복합 기본 키의 한 부분
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemKey", nullable = false) // 외래 키 컬럼 명시
    private Item item;

    @Column(name = "quantity", nullable = false)
    private Long quantity; // 상품 수량, BIGINT -> Long

    // 복합 기본 키 클래스 정의 (static inner class로 두는 경우가 많음)
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode // equals와 hashCode 메서드를 자동으로 생성 (복합키에 필수)
    public static class CartItemPk implements java.io.Serializable {
        private Long cart; // Cart 엔티티의 PK (customerKey)와 매핑
        private Long item; // Item 엔티티의 PK (itemKey)와 매핑
    }

    // ERD에 '담은날짜'가 있었는데, CartItem 생성 시점이므로 필요에 따라 추가
    // @Column(name = "added_date", nullable = false)
    // private LocalDate addedDate;
}
