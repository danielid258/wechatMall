package com.daniel.service;

import com.daniel.model.ProductCategory;

import java.util.List;

/**
 * Daniel on 2018/6/6.
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
