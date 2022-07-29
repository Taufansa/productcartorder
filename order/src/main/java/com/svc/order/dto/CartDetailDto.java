package com.svc.order.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartDetailDto {
    private UUID cartId;
    private String status;
    private List<SelectedProductDto> products;
}
