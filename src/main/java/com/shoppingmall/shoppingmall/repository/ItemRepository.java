package com.shoppingmall.shoppingmall.repository;

import com.shoppingmall.shoppingmall.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory_CategoryKey(Long categoryKey);
}