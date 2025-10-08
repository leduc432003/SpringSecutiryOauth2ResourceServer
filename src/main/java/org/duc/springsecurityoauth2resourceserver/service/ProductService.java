package org.duc.springsecurityoauth2resourceserver.service;

import lombok.RequiredArgsConstructor;
import org.duc.springsecurityoauth2resourceserver.model.Product;
import org.duc.springsecurityoauth2resourceserver.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }
}
