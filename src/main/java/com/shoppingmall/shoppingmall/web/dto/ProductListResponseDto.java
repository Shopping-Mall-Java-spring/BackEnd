package com.shoppingmall.shoppingmall.web.dto;

import com.shoppingmall.shoppingmall.domain.Item;
import lombok.Getter;

@Getter
public class ProductListResponseDto {
    private final Long itemKey;
    private final Long categoryKey;
    private final String iname;
    private final Long iprice;
    private final Long quantity;
    private final String item_desc;

    public ProductListResponseDto(Item item){
        this.itemKey = item.getItemKey();
        this.categoryKey = item.getCategory().getCategoryKey();
        this.iname = item.getName();
        this.iprice = item.getPrice();
        this.quantity = item.getQuantity();
        this.item_desc = item.getItemDesc();
    }
}
