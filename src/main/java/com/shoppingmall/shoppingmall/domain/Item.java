package com.shoppingmall.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemKey", nullable = false)
    private Long itemKey;

    // Category와의 ManyToOne 관계: 여러 상품이 하나의 카테고리에 속함
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 (카테고리 정보가 필요할 때만 로드)
    @JoinColumn(name = "categoryKey", nullable = false) // 외래 키 컬럼 명시
    private Category category; // Category 엔티티 객체

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price; // BIGINT는 Java에서 Long으로 매핑

    @Column(name = "quantity", nullable = false)
    private Long quantity; // 재고 수량, BIGINT -> Long

    @Column(name = "itemDesc", columnDefinition = "TEXT") // TEXT 타입 매핑
    private String itemDesc;

    // 양방향 관계 설정 (선택 사항)
    // Item과 OrderItem은 1:N 관계
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    // Item과 CartItem은 1:N 관계
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    // Item과 Review는 1:N 관계
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
