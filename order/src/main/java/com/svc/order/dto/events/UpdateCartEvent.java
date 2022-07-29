package com.svc.order.dto.events;

import com.svc.order.dto.BaseEvent;
import com.svc.order.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartEvent extends BaseEvent {
    public CartDto data;
}
