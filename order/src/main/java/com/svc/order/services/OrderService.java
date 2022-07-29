package com.svc.order.services;

import com.svc.order.dto.*;
import com.svc.order.dto.events.UpdateCartEvent;
import com.svc.order.dto.events.UpdateStockEvent;
import com.svc.order.entites.Order;
import com.svc.order.message.Publisher;
import com.svc.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Value("${link.product}")
    private String linkProduct;

    @Value("${link.cart}")
    private String linkCart;

    @Autowired
    private Publisher publisher;

    public Order checkout(UUID cartId){
        Order order = new Order();
        order.setCartId(cartId);
        order.setStatus("SUBMITTED");
        order.setTotalAmount(getTotalAmountFromCart(cartId));
        CartDetailDto cartDetailDto = this.fetchCartDetail(cartId);
        this.publicUpdateCart(cartDetailDto);
        for(int i = 0; i < cartDetailDto.getProducts().size(); i++){
            this.publishUpdateStock(cartDetailDto.getProducts().get(i));
        }
        return orderRepository.saveAndFlush(order);
    }

    private CartDetailDto fetchCartDetail(UUID cartId){
        RestTemplate restTemplate = new RestTemplate();
        CartDetailDto response = restTemplate.getForObject(linkCart+"/detail?cart_id="+cartId.toString(), CartDetailDto.class);
        return response;
    }

    private BigDecimal getTotalAmountFromCart(UUID cartId){
        CartDetailDto cartDetail = this.fetchCartDetail(cartId);
        BigDecimal totalOrder = BigDecimal.ZERO;
        for (int i = 0; i < cartDetail.getProducts().size(); i++){
            BigDecimal amountPerProduct = cartDetail.getProducts().get(i).getPrice().multiply(BigDecimal.valueOf(cartDetail.getProducts().get(i).getQty()));
            totalOrder = totalOrder.add(amountPerProduct);
        }
        return totalOrder;
    }

    private void publishUpdateStock(SelectedProductDto productDto){
        UpdateStockEvent updateStockEvent = new UpdateStockEvent();
        updateStockEvent.setEventId(UUID.randomUUID().toString());
        updateStockEvent.setEventType("updateProductStock");
        updateStockEvent.setEventHandler("updateProductStockHandler");
        updateStockEvent.setData(
                new UpdateStockEvent.UpdateData(
                        productDto.getProductId(),
                        productDto.getTitle(),
                        productDto.getUnit(),
                        productDto.getPrice(),
                        productDto.getQty(),
                        "")
        );
        publisher.publish(updateStockEvent);
    }

    private void publicUpdateCart(CartDetailDto cartDetailDto){
        UpdateCartEvent updateCartEvent = new UpdateCartEvent();
        updateCartEvent.setEventId(UUID.randomUUID().toString());
        updateCartEvent.setEventType("updateCartStatus");
        updateCartEvent.setEventHandler("updateCartStatusHandler");
        updateCartEvent.setData(
                new CartDto(
                        cartDetailDto.getCartId(),
                        "SUBMITTED"
                )
        );
        publisher.publish(updateCartEvent);
    }




}
