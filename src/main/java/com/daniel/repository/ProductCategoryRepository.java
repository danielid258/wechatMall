package com.daniel.repository;

import com.daniel.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Daniel on 2018/6/6.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
