package com.svc.order.message;

import com.svc.order.dto.BaseEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Publisher {

    @Value("${rabbitmq.exchange.orderExchange}")
    String orderExchange;

    @Value("${rabbitmq.key.orderRoutingKey}")
    String orderKey;

    private final RabbitTemplate rabbitTemplate;
    private final Environment environment;

    public Publisher(RabbitTemplate rabbitTemplate, Environment environment) {
        this.rabbitTemplate = rabbitTemplate;
        this.environment = environment;
    }

    public void publish(BaseEvent event){
        log.info("start publish "+event.getEventType());
        log.info("event : {}", event);
        rabbitTemplate.convertAndSend(orderExchange, orderKey+"."+event.getEventType(), event);
        log.info("end publish : " + event.getEventType());
    }
}
