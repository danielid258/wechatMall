package com.daniel.service;

import com.daniel.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * on 6/7/2018.
 */
public interface OrderService {
    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(Pageable pageable);

    /**
     * 查询openid所属的订单
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 查询openid所属的订单
     */
    void cancelOrder(String openid, String orderId);
}
