package com.daniel.service.impl;

import com.daniel.dto.OrderDTO;
import com.daniel.model.OrderDetail;
import com.daniel.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * on 6/7/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("tom");
        orderDTO.setBuyerPhone("13138888888");
        orderDTO.setBuyerAddress("my home");
        orderDTO.setBuyerOpenid("123654");
        orderDTO.setOrderDetailList(Arrays.asList(new OrderDetail("201806070010", 2), new OrderDetail("201806070012", 1)));


        orderService.create(orderDTO);

    }

    @Test
    public void findOne() throws Exception {

    }

    @Test
    public void findList() throws Exception {

    }

    @Test
    public void cancel() throws Exception {

    }

    @Test
    public void finish() throws Exception {

    }

    @Test
    public void paid() throws Exception {

    }

    @Test
    public void findList1() throws Exception {

    }

}