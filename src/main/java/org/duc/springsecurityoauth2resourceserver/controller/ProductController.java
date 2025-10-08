package org.duc.springsecurityoauth2resourceserver.controller;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.model.Product;
import org.duc.springsecurityoauth2resourceserver.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }
}
