package com.shoppingmall.shoppingmall.service;

import com.shoppingmall.shoppingmall.domain.CartItem;
import com.shoppingmall.shoppingmall.dto.CartItemListResponseDto;
import com.shoppingmall.shoppingmall.repository.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
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
    @Transactional
    public void deleteCartItem(Long customerKey, Long itemKey){
        CartItem.CartItemPk cartItemPk = new CartItem.CartItemPk(customerKey,itemKey);
        //삭제할 엔티티 존재여부 예외처리
        if(!cartItemRepository.existsById(cartItemPk)){
            throw new EntityNotFoundException("해당 장바구니 항목을 찾을 수 없습니다.");
        }
        //엔티티 객체 삭제
        cartItemRepository.deleteById(cartItemPk);
    }
}
