package com.svc.order.message;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.svc.order.dto.BaseEvent;
import com.svc.order.entites.Order;
import com.svc.order.repositories.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class Subscriber implements MessageListener {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void onMessage(Message message) {
        Gson gsonBaseEvent = new Gson();
        String messageBody = new String(message.getBody());

        try {
            var baseEvent = gsonBaseEvent.fromJson(messageBody, BaseEvent.class);

            if (baseEvent.getEventType().equals("orderPaid")) {

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(messageBody, JsonObject.class);
                String data = jsonObject.getAsJsonObject("data").toString();
                var order = gson.fromJson(data, Order.class);

                orderRepository.saveAndFlush(order);
                //update order status after paid

                log.info("::: end ::: {}", baseEvent.getEventType());

            } else {
                log.info("Event Type Not Registered");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
}
