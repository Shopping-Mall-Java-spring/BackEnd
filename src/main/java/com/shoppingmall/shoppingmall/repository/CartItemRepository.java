package com.shoppingmall.shoppingmall.repository;

import com.shoppingmall.shoppingmall.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItem.CartItemPk> {
    @Query("select ci from CartItem ci " +
            "join fetch ci.cart c " +
            "join fetch ci.item i " +
            "where c.customerKey = :customerKey")
    List<CartItem> findCartDetailsDtoList(@Param("customerKey") Long customerKey);
}