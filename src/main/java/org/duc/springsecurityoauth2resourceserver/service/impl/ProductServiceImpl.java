package org.duc.springsecurityoauth2resourceserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.model.Product;
import org.duc.springsecurityoauth2resourceserver.repository.ProductRepository;
import org.duc.springsecurityoauth2resourceserver.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
