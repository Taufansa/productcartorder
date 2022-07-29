package com.svc.product.message;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.svc.product.dto.BaseEvent;
import com.svc.product.dto.ProductDto;
import com.svc.product.entities.Product;
import com.svc.product.repositories.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class Subscriber implements MessageListener {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void onMessage(Message message) {
        Gson gsonBaseEvent = new Gson();
        String messageBody = new String(message.getBody());

        try {
            var baseEvent = gsonBaseEvent.fromJson(messageBody, BaseEvent.class);

            if (baseEvent.getEventType().equals("updateProductStock")) {

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(messageBody, JsonObject.class);
                String data = jsonObject.getAsJsonObject("data").toString();
                var productDto = gson.fromJson(data, ProductDto.class);

                Product product = productRepository.findById(productDto.getId()).get();
                Integer newStock = product.getStock() - productDto.getStock();
                if (newStock > 0) {
                    product.setStock(newStock);
                } else {
                    product.setStock(0);
                }

                productRepository.saveAndFlush(product);
                //update stock after checkout

                log.info("::: end ::: {}", baseEvent.getEventType());

            } else {
                log.info("Event Type Not Registered");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
}
