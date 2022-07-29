package com.svc.cart.message;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.svc.cart.dto.BaseEvent;
import com.svc.cart.entities.Cart;
import com.svc.cart.repositories.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class Subscriber implements MessageListener {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void onMessage(Message message) {
        Gson gsonBaseEvent = new Gson();
        String messageBody = new String(message.getBody());

        try {
            var baseEvent = gsonBaseEvent.fromJson(messageBody, BaseEvent.class);

            if (baseEvent.getEventType().equals("updateCartStatus")) {

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(messageBody, JsonObject.class);
                String data = jsonObject.getAsJsonObject("data").toString();
                var cart = gson.fromJson(data, Cart.class);

                cartRepository.save(cart);
                //update cart status after check out

                log.info("::: end ::: {}", baseEvent.getEventType());

            } else {
                log.info("Event Type Not Registered");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
}
