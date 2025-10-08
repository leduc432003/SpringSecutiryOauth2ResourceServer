package org.duc.springsecurityoauth2resourceserver.service;

import org.duc.springsecurityoauth2resourceserver.model.Product;
import org.duc.springsecurityoauth2resourceserver.repository.ProductRepository;

public interface ProductService {
    Product createProduct(Product product);
}
