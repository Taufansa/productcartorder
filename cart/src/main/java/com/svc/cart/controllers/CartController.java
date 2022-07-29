package com.svc.cart.controllers;

import com.svc.cart.dto.CartDetailDto;
import com.svc.cart.dto.CartDto;
import com.svc.cart.entities.SelectedProduct;
import com.svc.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/all")
    public List<CartDetailDto> allCarts(@RequestParam("status") String status){
        return cartService.findAllCart(status);
    }

    @PostMapping("/add")
    public SelectedProduct addToCart(@RequestBody CartDto cartDto){
        return cartService.addToCart(cartDto);
    }

    @PostMapping("/detail")
    public CartDetailDto cartDetail(@RequestParam("cart_id") UUID cartId){
        return cartService.cartDetail(cartId);
    }

}
