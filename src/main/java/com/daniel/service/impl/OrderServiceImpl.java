package com.daniel.service.impl;

import com.daniel.dto.CartDTO;
import com.daniel.dto.OrderDTO;
import com.daniel.enums.OrderStatusEnum;
import com.daniel.enums.PayStatusEnum;
import com.daniel.enums.ResultEnum;
import com.daniel.exception.SellException;
import com.daniel.model.OrderDetail;
import com.daniel.model.OrderMaster;
import com.daniel.model.Product;
import com.daniel.repository.OrderDetailRepository;
import com.daniel.repository.OrderRepository;
import com.daniel.service.*;
import com.daniel.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * on 6/7/2018.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    //@Autowired
    //private PaymentService paymentService;
    //
    //@Autowired
    //private MessagePushService messagePushService;
    //
    //@Autowired
    //private WebSocket webSocket;


    /**
     * create order
     * <p>
     * client input: productId,productQuantity
     *
     * @param orderDTO
     * @return
     */
    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail detail : orderDTO.getOrderDetailList()) {
            Product product = productService.findOne(detail.getProductId());
            if (product == null)
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);

            //amount = price * quantity
            //orderAmount += amount
            //notice: price should query from db
            orderAmount = product.getProductPrice().multiply(BigDecimal.valueOf(detail.getProductQuantity())).add(orderAmount);

            //save order detail
            BeanUtils.copyProperties(product, detail);
            detail.setDetailId(KeyUtil.genUniqueKey());
            detail.setOrderId(orderId);
            orderDetailRepository.save(detail);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderRepository.save(orderMaster);

        //decrease stock quantity
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        //send webSocket message
        //webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
