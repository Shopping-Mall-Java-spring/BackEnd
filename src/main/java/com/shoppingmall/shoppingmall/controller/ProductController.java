package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.service.ItemService;
import com.shoppingmall.shoppingmall.dto.ProductListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ItemService itemService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductListResponseDto>> getProducts(
            @RequestParam(name="categoryKey", required = false) Long categoryKey){

        List<ProductListResponseDto> products = itemService.findProducts(categoryKey);
        return ResponseEntity.ok(products);
    }
}