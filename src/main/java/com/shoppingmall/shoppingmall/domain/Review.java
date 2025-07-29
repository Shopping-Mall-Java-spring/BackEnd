package com.shoppingmall.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewKey", nullable = false)
    private Long reviewKey;

    // Customer와의 ManyToOne 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerKey", nullable = false)
    private Customer customer;

    // Item과의 ManyToOne 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemKey", nullable = false)
    private Item item;

    @Column(name = "title", length = 30, nullable = false) // ERD의 '제목(Name)'을 'title'로 변경
    private String title;

    @Column(name = "starRating", nullable = false) // 별점
    private Integer starRating; // BIGINT -> Integer (보통 1-5점은 Integer로 충분)

    @Column(name = "date", nullable = false) // ERD의 '날짜(Day)'를 'reviewDate'로 변경
    private LocalDate reviewDate; // DATE -> LocalDate

    @Column(name = "content", columnDefinition = "TEXT") // ERD의 '내용(Text)'을 'content'로 변경
    private String content;

    // 리뷰 생성 시 날짜 자동 설정 (필요하다면)
    @PrePersist
    public void prePersist() {
        this.reviewDate = LocalDate.now();
    }
}