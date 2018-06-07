package com.daniel.service.impl;

import com.daniel.dto.CartDTO;
import com.daniel.enums.ProductStatusEnum;
import com.daniel.enums.ResultEnum;
import com.daniel.exception.SellException;
import com.daniel.model.Product;
import com.daniel.repository.ProductRepository;
import com.daniel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * on 6/7/2018.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findOne(String productId) {
        return productRepository.findOne(productId);
    }

    @Override
    public List<Product> findUpAll() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product save(Product productInfo) {
        return productRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(cartDTO -> {
            Product product = getProduct(cartDTO.getProductId());

            updateProductStock(product, product.getProductStock() + cartDTO.getProductQuantity());
        });
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        cartDTOList.forEach(cartDTO -> {
            Product product = getProduct(cartDTO.getProductId());

            int quantity = product.getProductStock() - cartDTO.getProductQuantity();
            if (quantity < 0)
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);

            updateProductStock(product, quantity);
        });

    }

    @Override
    public Product onSale(String productId) {
        Product product = getProduct(productId);

        if (product.getProductStatusEnum() == ProductStatusEnum.UP)
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);

        product.setProductStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(product);
    }

    @Override
    public Product offSale(String productId) {
        Product product = getProduct(productId);

        if (product.getProductStatusEnum() == ProductStatusEnum.DOWN)
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);

        product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productRepository.save(product);
    }

    private Product getProduct(String productId) {
        Product product = findOne(productId);
        if (product == null)
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        return product;
    }

    private void updateProductStock(Product product, int quantity) {
        product.setProductStock(quantity);
        productRepository.save(product);
    }
}
