package com.svc.cart.services;

import com.svc.cart.dto.CartDetailDto;
import com.svc.cart.dto.CartDto;
import com.svc.cart.entities.Cart;
import com.svc.cart.entities.SelectedProduct;
import com.svc.cart.repositories.CartRepository;
import com.svc.cart.repositories.SelectedProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SelectedProductsRepository selectedProductsRepository;

    public SelectedProduct addToCart(CartDto cartDto){
        if (Objects.isNull(cartDto.getProduct().getCartId())) {
            Cart cart = new Cart();
            cart.setStatus("OPEN");
            Cart savedCart = cartRepository.saveAndFlush(cart);
            SelectedProduct selectedProduct = cartDto.getProduct();
            selectedProduct.setCartId(savedCart.getId());
            return selectedProductsRepository.saveAndFlush(selectedProduct);
        } else {
            return selectedProductsRepository.saveAndFlush(cartDto.getProduct());
        }
    }

    public CartDetailDto cartDetail (UUID cartId){
        Cart cart = cartRepository.getReferenceById(cartId);
        List<SelectedProduct> products = selectedProductsRepository.findAllByCartId(cartId);
        return CartDetailDto.builder()
                .cartId(cart.getId())
                .status(cart.getStatus())
                .products(products)
                .build();
    }

    public List<CartDetailDto> findAllCart(String status){
        List<CartDetailDto> result = new ArrayList<>();
        List<Cart> carts = cartRepository.findAllByStatus(status);
        for (int i = 0; i < carts.size(); i++){
            List<SelectedProduct> products = selectedProductsRepository.findAllByCartId(carts.get(i).getId());
            result.add(
                    CartDetailDto.builder()
                            .cartId(carts.get(i).getId())
                            .status(carts.get(i).getStatus())
                            .products(products)
                            .build()
            );
        }
        return result;
    }

}
