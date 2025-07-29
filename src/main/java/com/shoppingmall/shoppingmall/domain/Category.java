package com.shoppingmall.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA 엔티티임을 명시
@Table(name = "category") // 데이터베이스 테이블 이름 지정 (클래스명과 같으면 생략 가능)
@Getter // Lombok: getter 메서드 생성
@Setter // Lombok: setter 메서드 생성
@NoArgsConstructor // Lombok: 기본 생성자 자동 생성 (JPA 필수)
@AllArgsConstructor // Lombok: 모든 필드를 인자로 받는 생성자 자동 생성
@Builder // Lombok: 빌더 패턴을 사용하여 객체 생성 가능 (선택 사항, 편리함)
public class Category {

    @Id // 기본 키(Primary Key)임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략 (DB에 맡김)
    @Column(name = "categorykey", nullable = false) // 컬럼 이름 및 제약 조건
    private Long categoryKey;

    @Column(name = "categoryName", length = 10, nullable = false, unique = true) // VARCHAR(10) 및 고유 제약 조건 추가
    private String categoryName;

    // 양방향 관계 설정 (선택 사항이지만 편리함)
    // Category와 Item은 1:N 관계: 하나의 카테고리는 여러 상품을 가질 수 있음
    // mappedBy는 'Item' 엔티티의 'category' 필드에 의해 매핑된다는 것을 명시
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
}
