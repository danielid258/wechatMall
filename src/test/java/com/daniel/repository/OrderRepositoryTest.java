package com.daniel.repository;

import com.daniel.model.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * on 6/7/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void add() throws Exception {
        OrderMaster master = new OrderMaster();
        master.setOrderId("00001");
        master.setBuyerName("jack");
        master.setBuyerPhone("1888888888");
        master.setBuyerAddress("just a test");
        master.setBuyerOpenid("123654");
        master.setOrderAmount(new BigDecimal(56.85));
        master.setCreateTime(new Date());
        master.setUpdateTime(new Date());

        orderRepository.save(master);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        Page<OrderMaster> page = orderRepository.findByBuyerOpenid("123654", new PageRequest(0, 10));
        page.getContent().forEach(orderMaster -> log.info(orderMaster.toString()));
    }

}