package com.imooc.demo.service;

import com.imooc.demo.dto.OrderDTO;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface BuyerService {
  //查询一个订单
  OrderDTO findOrderOne(String openId,String orderId);
  //取消订单
  OrderDTO cancelOrder(String openID,String orderId);

}
