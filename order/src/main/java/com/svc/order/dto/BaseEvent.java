package com.svc.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.svc.order.util.GeneralUtil;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEvent {

    private String eventId;
    private String eventType;
    private String eventHandler;
    private String timestamp;
    private String createdBy;
    private String eventReferenceId;

    public BaseEvent() {
        eventId = UUID.randomUUID().toString();
        this.eventType = GeneralUtil.toCamelCase(this.getClass().getSimpleName());
        this.eventHandler = this.getClass().getSimpleName() + "Handler";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        this.timestamp = df.format(new Date());

    }

    public BaseEvent(String id, String eventType) {
        eventId = id;
        this.eventType = eventType;
        this.eventHandler = GeneralUtil.toCamelCase(this.getClass().getSimpleName()) + "Handler";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        this.timestamp = df.format(new Date());
    }

    @Override
    public String toString() {
        return this.getEventType();
    }

}
