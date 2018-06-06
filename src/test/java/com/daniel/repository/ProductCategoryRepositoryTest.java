package com.daniel.repository;

import com.daniel.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Daniel on 2018/6/6.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        log.info(productCategory.toString());
    }

    @Test
    public void add() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("火热商品");
        productCategory.setCategoryType(1);
        productCategory.setCreateTime(new Date());
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void update() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryName("best");
        productCategory.setUpdateTime(new Date());
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1, 2, 4);
        List<ProductCategory> productCategories = productCategoryRepository.findByCategoryTypeIn(list);

        productCategories.forEach(productCategory -> log.info(productCategory.toString()));
    }
}