package com.daniel.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * on 6/7/2018.
 */
@Data
public class CategoryVO {
    @JsonProperty("category")
    private Integer categoryType;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("products")
    private List<ProductVO> productVOList;
}
