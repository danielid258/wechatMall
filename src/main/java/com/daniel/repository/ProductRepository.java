package com.daniel.repository;

import com.daniel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * on 6/7/2018.
 */
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductStatus(Integer productStatus);
}
