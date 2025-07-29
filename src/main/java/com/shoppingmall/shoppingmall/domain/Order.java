package com.shoppingmall.shoppingmall.domain;

import com.shoppingmall.shoppingmall.domain.enums.DeliveryRequest;
import com.shoppingmall.shoppingmall.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 'order'는 SQL 예약어일 수 있으므로 'orders'로 변경 권장
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderKey", nullable = false)
    private Long orderKey;

    // Customer와의 ManyToOne 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerKey", nullable = false)
    private Customer customer;

    @Column(name = "orderAddress", nullable = false)
    private String orderAddress;

    @Column(name = "name", nullable = false) // 받는분 성명
    private String recipientName;

    @Column(name = "phone", nullable = false, length = 20) // 받는분 휴대폰, VARCHAR(20)으로 변경
    private String recipientPhone;

    @Enumerated(EnumType.STRING) // Enum을 String으로 DB에 저장
    @Column(name = "deliveryRequest")
    private DeliveryRequest orderRequest;

    @Enumerated(EnumType.STRING) // Enum을 String으로 DB에 저장
    @Column(name = "paymentMethod", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "totalAmount", nullable = false)
    private Long totalAmount; // BIGINT -> Long

    @Column(name = "deliveryFee", nullable = false)
    private Integer deliveryFee; // INT -> Integer

    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate; // 주문 날짜, DATE -> LocalDate

    // 양방향 관계 설정 (선택 사항)
    // Order와 OrderItem은 1:N 관계
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 주문 생성 시 날짜 자동 설정 (필요하다면)
    @PrePersist
    public void prePersist() {
        this.orderDate = LocalDate.now();
    }
}
