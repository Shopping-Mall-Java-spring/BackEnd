package com.shoppingmall.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// (orderKey, itemKey) 조합의 유니크 제약 조건을 엔티티 레벨에 추가 (복합 기본 키)
@IdClass(OrderItem.OrderItemPk.class)
public class OrderItem {

    // Order와의 ManyToOne 관계
    @Id // 복합 기본 키의 한 부분
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderKey", nullable = false)
    private Order order;

    // Item과의 ManyToOne 관계
    @Id // 복합 기본 키의 한 부분
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemKey", nullable = false)
    private Item item;

    @Column(name = "quantity", nullable = false)
    private Long quantity; // BIGINT -> Long

    @Column(name = "priceAtTimeOfPurchase", nullable = false)
    private Long priceAtTimeOfPurchase; // BIGINT -> Long

    // 복합 기본 키 클래스 정의
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class OrderItemPk implements java.io.Serializable {
        private Long order; // Order 엔티티의 PK (orderKey)와 매핑
        private Long item;  // Item 엔티티의 PK (itemKey)와 매핑
    }
}
