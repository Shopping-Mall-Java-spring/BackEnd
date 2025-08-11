package com.shoppingmall.shoppingmall.controller;

import com.shoppingmall.shoppingmall.domain.CartItem;
import com.shoppingmall.shoppingmall.dto.CartItemListResponseDto;
import com.shoppingmall.shoppingmall.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    //장바구니 목록 조회
    @GetMapping("/{customerKey}")
    public ResponseEntity<List<CartItemListResponseDto>> getCartItemList(@PathVariable("customerKey") Long customerKey){
        List<CartItemListResponseDto> cartItemList = cartItemService.getCartList(customerKey);
        return ResponseEntity.ok(cartItemList);
    }
    //장바구니 삭제
    @DeleteMapping("/{customerKey}/items/{itemKey}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long customerKey, @PathVariable Long itemKey){
        cartItemService.deleteCartItem(customerKey, itemKey);

        return ResponseEntity.noContent().build();
    }
}
