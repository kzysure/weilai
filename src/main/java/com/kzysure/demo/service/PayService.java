package com.kzysure.demo.service;

import com.kzysure.demo.dto.OrderDTO;

/**
 * 支付.
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface PayService {
  //发起支付
  void create(OrderDTO orderDTO);

}
