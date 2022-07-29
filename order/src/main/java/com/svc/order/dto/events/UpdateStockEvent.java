package com.svc.order.dto.events;

import com.svc.order.dto.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockEvent extends BaseEvent {
    private UpdateData data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateData{
        private UUID id;

        private String title;

        private String unit;

        private BigDecimal price;

        private Integer stock;

        private String status;
    }
}
