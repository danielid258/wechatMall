package com.daniel.model;

import com.daniel.enums.ProductStatusEnum;
import com.daniel.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * on 6/6/2018.
 */
@Entity
@Data
public class Product {
    @Id
    private String productId;

    /**
     *名字
     */
    private String productName;

    /**
     *单价
     */
    private BigDecimal productPrice;

    /**
     *库存
     */
    private Integer productStock;

    /**
     *描述
     */
    private String productDescription;

    /**
     *产品图标URL
     */
    private String productIcon;

    /**
     *状态 0正常1下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    /**
     *类目编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
