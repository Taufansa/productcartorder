package com.svc.cart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "selected_products")
public class SelectedProduct {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    private UUID cartId;

    private UUID productId;

    private String title;

    private String unit;

    private BigDecimal price;

    private Integer qty;

}
