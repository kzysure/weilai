package com.kzysure.demo.controller;

import com.kzysure.demo.dto.OrderDTO;
import com.kzysure.demo.enums.ResultEnums;
import com.kzysure.demo.exception.SellException;
import com.kzysure.demo.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {
  @Autowired
  OrderService orderService;
  /**
   * 订单列表
   * @param page
   * @param size
   * @return
   */
  @GetMapping("/list")
  public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "10") Integer size,
                          Map<String,Object> map){
    PageRequest pageRequest = new PageRequest(page-1,size);
    Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
    map.put("orderDTOPage",orderDTOPage);
    map.put("currentPage",page);
    map.put("prePage",page-1);
    map.put("nextPage",page+1);
    return new ModelAndView("order/list",map);
  }
  @GetMapping("/cancel")
  public ModelAndView cancel(@RequestParam("orderId") String orderId,Map<String,Object> map){
    try{
      OrderDTO orderDTO = orderService.findOne(orderId);
      OrderDTO orderDTO1 = orderService.cancel(orderDTO);

    }catch(SellException e){
      log.error("【卖家端取消订单】，查询不到订单{}",e);
      map.put("msg", e.getMessage());
      map.put("url","/sell/seller/order/list");
      return new ModelAndView("common/error",map);
    }
    map.put("msg", ResultEnums.ORDER_CANCEL_SUCCESS.getMsg());
    map.put("url","/sell/seller/order/list");
    return new ModelAndView("common/success",map);


  }
  @GetMapping("/detail")
  public ModelAndView detail(@RequestParam("orderId")  String orderId,Map<String,Object> map){
    OrderDTO orderDTO = new OrderDTO();
    try {
      orderDTO = orderService.findOne(orderId);
    }catch (SellException e){
      log.error("【卖家端订单详情】，查询不到订单{}",e);
      map.put("msg", e.getMessage());
      map.put("url","/sell/seller/order/list");
      return new ModelAndView("common/error",map);
    }
    map.put("orderDTO",orderDTO);
    map.put("url","/sell/seller/order/list");
    return  new ModelAndView("order/detail",map);
  }
  @GetMapping("/finish")
  public ModelAndView finish(@RequestParam("orderId") String orderId,Map<String,Object> map){
    OrderDTO orderDTO = new OrderDTO();
    try {
      orderDTO = orderService.findOne(orderId);
    }catch (SellException e){
      log.error(ResultEnums.ORDER_NOT_EXIST.getMsg());
      map.put("msg",ResultEnums.ORDER_NOT_EXIST.getMsg());
      map.put("url","/sell/seller/order/list");
      return new ModelAndView("order/error",map);
    }
    OrderDTO orderDTO1 = orderService.finish(orderDTO);
    map.put("msg",ResultEnums.SUCCESS.getMsg());
    map.put("url","/sell/seller/order/list");
    return new ModelAndView("common/success",map);
  }

}
