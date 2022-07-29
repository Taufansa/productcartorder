package com.svc.cart.dto;

import com.svc.cart.entities.SelectedProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private SelectedProduct product;
}
