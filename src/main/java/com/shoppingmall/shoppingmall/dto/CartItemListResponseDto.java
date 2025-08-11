package com.shoppingmall.shoppingmall.dto;

import com.shoppingmall.shoppingmall.domain.CartItem;
import lombok.Getter;

@Getter
public class CartItemListResponseDto {
    private final Long itemKey;
    private final Long customKey;
    private final Long quantity;

    public CartItemListResponseDto(CartItem cartItem){
        this.itemKey = cartItem.getItem().getItemKey();
        this.customKey = cartItem.getCart().getCustomerKey();
        this.quantity = cartItem.getQuantity();
    }
}
