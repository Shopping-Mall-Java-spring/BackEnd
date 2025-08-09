package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.service.ItemService;
import com.shoppingmall.shoppingmall.dto.ProductListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ItemService itemService;

    //전체 상품 조회
    @GetMapping("/products")
    public ResponseEntity<List<ProductListResponseDto>> getAllProducts() {
        List<ProductListResponseDto> products = itemService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    //특정 카테고리 상품 조회
    @GetMapping("/categories/{categoryKey}/products")
    public ResponseEntity<List<ProductListResponseDto>> getProductsByCategory(@PathVariable Long categoryKey) {
        List<ProductListResponseDto> products = itemService.findProductByCategory(categoryKey);
        return ResponseEntity.ok(products);
    }
}