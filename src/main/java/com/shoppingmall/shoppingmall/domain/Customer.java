package com.shoppingmall.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerKey", nullable = false)
    private Long customerKey;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true) // UNIQUE 제약 조건 추가
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false, length = 20) // VARCHAR(20)으로 변경 (하이픈 등 고려)
    private String phone;

    // 양방향 관계 설정 (선택 사항)
    // Customer와 Cart는 1:1 관계 (권장) 또는 1:N 관계 (ERD상 1:N로 보여짐)
    // ERD에서는 Customer 1 : N Cart 로 보였지만, 논리적으로 1:1이 더 일반적이므로 아래는 1:1 기준으로 작성.
    // 만약 한 고객이 여러 장바구니를 가질 수 있다면 (예: 위시리스트처럼), @OneToMany로 두고 mappedBy = "customer"
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Cart cart; // 1:1 관계 (OneToOne)

    // Customer와 Order는 1:N 관계
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    // Customer와 Review는 1:N 관계
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    // **주의:** 만약 한 고객이 여러 활성화된 장바구니를 가질 수 있도록 하려면
    // @OneToOne 대신 @OneToMany를 사용하고 List<Cart> carts = new ArrayList<>(); 로 변경해야 합니다.
    // 하지만 대부분의 쇼핑몰은 Customer당 하나의 활성화된 장바구니를 가집니다.
}
