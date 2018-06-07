package com.daniel.controller;

import com.daniel.model.Product;
import com.daniel.model.ProductCategory;
import com.daniel.service.ProductCategoryService;
import com.daniel.service.ProductService;
import com.daniel.util.ResultVOUtil;
import com.daniel.vo.CategoryVO;
import com.daniel.vo.ProductVO;
import com.daniel.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * on 6/7/2018.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        List<Product> upProducts = productService.findUpAll();

        List<Integer> categories = upProducts.stream().map(Product::getCategoryType).collect(Collectors.toList());

        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categories);

        List<CategoryVO> categoryVOs = new ArrayList<>();
        categoryList.forEach(productCategory -> {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCategoryType(productCategory.getCategoryType());
            categoryVO.setCategoryName(productCategory.getCategoryName());

            List<ProductVO> productVOs = new ArrayList<>();
            upProducts.forEach(product -> {
                if (product.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(product, productVO);
                    productVOs.add(productVO);
                }
            });
            categoryVO.setProductVOList(productVOs);
            categoryVOs.add(categoryVO);
        });
        return ResultVOUtil.success(categoryVOs);
    }
}
