package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.domain.CartItem;
import com.shoppingmall.shoppingmall.dto.CartItemListResponseDto;
import com.shoppingmall.shoppingmall.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItemListResponseDto> getCartList(Long customerKey){
        List<CartItem> cartItems = cartItemRepository.findCartDetailsDtoList(customerKey);
        return cartItems.stream()
                .map(CartItemListResponseDto::new)
                .collect(Collectors.toList());
    }
}
