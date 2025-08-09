package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.repository.ItemRepository;
import com.shoppingmall.shoppingmall.dto.ProductListResponseDto;
import com.shoppingmall.shoppingmall.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    //1. 모든 상품 조회
    public List<ProductListResponseDto> findProducts(Long categoryKey){
        List<Item> items;
        //전체 상품 조회 -> 파라미터 없을 떄
        if(categoryKey==null){
            items = itemRepository.findAll();
        }
        //카테고리 필터링 조회
        else{
            items = itemRepository.findByCategory_CategoryKey(categoryKey);
        }
        return items.stream()
                .map(ProductListResponseDto::new)
                .collect(Collectors.toList());
    }
}
