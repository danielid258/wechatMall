package com.daniel.service.impl;

import com.daniel.constant.SysConstant;
import com.daniel.dto.OrderDTO;
import com.daniel.enums.ResultEnum;
import com.daniel.exception.SellException;
import com.daniel.service.OrderService;
import com.daniel.service.PaymentService;
import com.daniel.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * on 6/8/2018.
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    //@Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(SysConstant.ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("[weChat payment beginning] request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[weChat payment beginning] response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[weChat payment] asynchronous notification, payResponse={}", JsonUtil.toJson(payResponse));

        //query order information
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if (orderDTO == null) {
            log.error("[weChat payment] asynchronous notification: order does not exist, orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //determine whether the amount is the same
        if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            log.error("[weChat payment] asynchronous notification: inconsistent order amount, orderId={}, weChat amount={}, system amount={}", payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //update order status
        orderService.paid(orderDTO);

        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("[weChat refund] request={}", JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[weChat refund] response={}", JsonUtil.toJson(refundResponse));

        return refundResponse;
    }
}
