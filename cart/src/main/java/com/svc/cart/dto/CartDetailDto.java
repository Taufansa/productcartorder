package com.svc.cart.dto;

import com.svc.cart.entities.SelectedProduct;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartDetailDto {
    private UUID cartId;
    private String status;
    private List<SelectedProduct> products;
}
