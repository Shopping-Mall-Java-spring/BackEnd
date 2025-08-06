package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.domain.ItemRepository;
import com.shoppingmall.shoppingmall.web.dto.ProductListResponseDto;
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
    public List<ProductListResponseDto> findAllProducts(){
        return itemRepository.findAll().stream()
                .map(ProductListResponseDto::new)
                .collect(Collectors.toList());
    }
    //2. 선택된 카테고리 상품 조회
    public List<ProductListResponseDto> findProductByCategory(Long categoryKey){
        return itemRepository.findByCategory_CategoryKey(categoryKey).stream()
                .map(ProductListResponseDto::new)
                .collect(Collectors.toList());
    }
}
