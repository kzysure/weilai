package com.kzysure.demo.service.impl;

import com.kzysure.demo.dto.OrderDTO;
import com.kzysure.demo.enums.ResultEnums;
import com.kzysure.demo.exception.SellException;
import com.kzysure.demo.service.BuyerService;
import com.kzysure.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
@Autowired
  OrderService orderService;
  @Override
  public OrderDTO findOrderOne(String openId, String orderId) {
      return checkOrderOwner(openId,orderId);

  }

  @Override
  public OrderDTO cancelOrder(String openID, String orderId) {
    OrderDTO orderDTO = orderService.findOne(orderId);
    if (orderDTO == null){
      log.error("【取消订单】订单不存在");
      throw new SellException(ResultEnums.PARAM_ERROR);
    }
    return orderService.cancel(orderDTO);
  }
  public OrderDTO checkOrderOwner(String openId,String orderId){
    OrderDTO orderDTO = orderService.findOne(orderId);
    if (orderDTO ==null){
      return null;
    }
    //判断时候属于自己的订单
    if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
      log.error("【查询订单】,订单openId不一致，openId={},oderDTO={}",openId,orderDTO);
      throw new SellException(ResultEnums.ORDER_OWNER_ERROR);
    }
    return orderDTO;
  }
}
