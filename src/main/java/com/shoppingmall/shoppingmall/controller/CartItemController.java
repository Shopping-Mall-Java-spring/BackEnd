package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.domain.CartItem;
import com.shoppingmall.shoppingmall.dto.CartItemListResponseDto;
import com.shoppingmall.shoppingmall.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping("/{customerKey}")
    public ResponseEntity<List<CartItemListResponseDto>> getCartItemList(@PathVariable("customerKey") Long customerKey){
        List<CartItemListResponseDto> cartItemList = cartItemService.getCartList(customerKey);
        return ResponseEntity.ok(cartItemList);
    }
}
