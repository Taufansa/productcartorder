package com.svc.product.controllers;

import com.svc.product.dto.GeneralDto;
import com.svc.product.dto.ProductDto;
import com.svc.product.entities.Product;
import com.svc.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> findAll(@RequestParam("status") String status){
        return productService.findAll(status);
    }

    @GetMapping("/selected")
    public GeneralDto<List<Product>> findAllByIds(@RequestParam("product_ids") List<UUID> productIds){
        return new GeneralDto(productService.getAllProductByProductIds(productIds));
    }

    @GetMapping("/detail")
    public Product getProduct(@RequestParam("product_id") UUID productId){
        return productService.findProductDetail(productId);
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody ProductDto productDto){
        return productService.saveProduct(productDto);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam("product_id") UUID productId){
        productService.deleteProduct(productId);
    }

}
