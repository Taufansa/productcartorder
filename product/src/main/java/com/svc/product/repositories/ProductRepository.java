package com.svc.product.repositories;

import com.svc.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByStatus(String status);

    List<Product> findAllByIdIn(List<UUID> id);
}
