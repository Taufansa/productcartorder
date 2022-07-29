package com.svc.cart.repositories;

import com.svc.cart.entities.SelectedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SelectedProductsRepository extends JpaRepository<SelectedProduct, UUID> {

    List<SelectedProduct> findAllByCartId(UUID cartId);

}
