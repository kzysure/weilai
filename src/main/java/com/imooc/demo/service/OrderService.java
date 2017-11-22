package com.imooc.demo.service;

import com.imooc.demo.dto.OrderDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OrderService {
  /** 创建订单. */
  OrderDTO createOrder(OrderDTO orderDTO);

  /** 查询单个订单. */
  OrderDTO findOne(String orderId);

  /** 查询订单列表. */
  Page<OrderDTO> findList(String buyerOpenid,Pageable pageable);

  /** 取消订单. */
  OrderDTO cancel(OrderDTO orderDTO);


  /** 完结订单. */
  OrderDTO finish(OrderDTO orderDTO);


  /** 支付订单. */
  OrderDTO paid(OrderDTO orderDTO);
  /** 查询所有订单，后台用*/
  Page<OrderDTO> findList (Pageable pageable);

}
