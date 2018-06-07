package com.daniel.service;

import com.daniel.dto.CartDTO;
import com.daniel.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Daniel on 2018/6/6.
 */
public interface ProductService {
    Product findOne(String productId);

    List<Product> findUpAll();

    Page<Product> findAll(Pageable pageable);

    Product save(Product productInfo);

    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);

    Product onSale(String productId);

    Product offSale(String productId);
}
