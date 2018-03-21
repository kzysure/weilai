package com.kzysure.demo.controller;

import com.kzysure.demo.dto.OrderDTO;
import com.kzysure.demo.enums.ResultEnums;
import com.kzysure.demo.exception.SellException;
import com.kzysure.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {
  @Autowired
  private OrderService orderService;
@GetMapping("/create")
  public void create(@RequestParam("orderId") String orderId,@RequestParam("returnUrl") String returnUrl){
  //1.查询订单
  OrderDTO orderDTO = orderService.findOne(orderId);
  if (orderDTO ==null){
    throw new SellException(ResultEnums.ORDER_NOT_EXIST);
  }
  //2.发起支付

}
}
