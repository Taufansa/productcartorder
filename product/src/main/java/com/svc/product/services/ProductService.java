package com.svc.product.services;

import com.svc.product.dto.ProductDto;
import com.svc.product.entities.Product;
import com.svc.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(String status){
        return productRepository.findAllByStatus(status);
    }

    public Product findProductDetail(UUID id){
        return productRepository.findById(id).get();
    }

    public Product saveProduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(product.getTitle());
        product.setPrice(productDto.getPrice());
        product.setStatus(productDto.getStatus());
        product.setStock(product.getStock());
        product.setUnit(product.getUnit());
        return productRepository.saveAndFlush(product);
    }

    public Product updateProduct(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId()).get();
        product.setTitle(product.getTitle());
        product.setPrice(productDto.getPrice());
        product.setStatus(productDto.getStatus());
        product.setStock(product.getStock());
        product.setUnit(product.getUnit());
        return productRepository.saveAndFlush(product);
    }

    public void deleteProduct(UUID id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllProductByProductIds(List<UUID> productIds){
        return productRepository.findAllByIdIn(productIds);
    }

}
