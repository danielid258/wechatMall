package com.daniel.repository;

import com.daniel.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * on 6/7/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void add(){
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("00002");
        detail.setOrderId("00001");
        detail.setProductId("201806070012");
        detail.setProductName("周黑鸭");
        detail.setProductPrice(new BigDecimal(88.88));
        detail.setProductQuantity(5);
        detail.setProductIcon("");

        orderDetailRepository.save(detail);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> details = orderDetailRepository.findByOrderId("00001");
        details.forEach(orderDetail -> log.info(orderDetail.toString()));
    }
}