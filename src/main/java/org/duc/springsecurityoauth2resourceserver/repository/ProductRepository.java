package org.duc.springsecurityoauth2resourceserver.repository;

import org.duc.springsecurityoauth2resourceserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
