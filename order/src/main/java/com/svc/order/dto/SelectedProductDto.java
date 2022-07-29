package com.svc.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedProductDto {
    private UUID id;

    private UUID cartId;

    private UUID productId;

    private String title;

    private String unit;

    private BigDecimal price;

    private Integer qty;
}
