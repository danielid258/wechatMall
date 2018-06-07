package com.daniel.service.impl;

import com.daniel.dto.CartDTO;
import com.daniel.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * on 6/7/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void increaseStock() throws Exception {
        List<CartDTO> cartDTOs = Arrays.asList(new CartDTO("201806070012", 10));
        productService.increaseStock(cartDTOs);
    }

    @Test
    public void decreaseStock() {
        List<CartDTO> cartDTOs = Arrays.asList(new CartDTO("201806070012", 100));
        productService.decreaseStock(cartDTOs);
    }
}