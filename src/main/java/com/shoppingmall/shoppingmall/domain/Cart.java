package com.shoppingmall.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    // CustomerKey를 PK이자 FK로 사용하여 1:1 관계를 표현 (한 고객당 하나의 장바구니)
    @Id
    @Column(name = "customerKey", nullable = false)
    private Long customerKey;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("customerKey") // customerKey가 PK이자 Customer 엔티티의 FK임을 명시
    @JoinColumn(name = "customerKey") // 컬럼 이름 명시
    private Customer customer;

    @Column(name = "rdate", nullable = false)
    private LocalDate createdDate; // 장바구니 생성일

    // 양방향 관계 설정 (선택 사항)
    // Cart와 CartItem은 1:N 관계
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    // 초기 생성 시 생성일 자동 설정 (필요하다면)
    @PrePersist // 엔티티가 영속성 컨텍스트에 저장되기 전에 실행
    public void prePersist() {
        this.createdDate = LocalDate.now();
    }
}
