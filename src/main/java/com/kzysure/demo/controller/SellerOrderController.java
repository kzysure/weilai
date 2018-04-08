package com.kzysure.demo.controller;

import com.kzysure.demo.VO.OrderVO;
import com.kzysure.demo.VO.ResultVO;
import com.kzysure.demo.dataobject.OrderMaster;
import com.kzysure.demo.dto.OrderDTO;
import com.kzysure.demo.enums.ResultEnums;
import com.kzysure.demo.exception.SellException;
import com.kzysure.demo.repository.OrderMasterRepository;
import com.kzysure.demo.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.swing.BakedArrayList;

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
  @Autowired
  OrderMasterRepository orderMasterRepository;
  /**
   * 订单列表
   * @return
   */
  @GetMapping("/list")
  @ResponseBody
  public List<OrderVO> list(){
  List<OrderMaster> orderMasterList = orderMasterRepository.findAllByOrderByCreateTimeDesc();
    System.out.println(orderMasterList);
  List<OrderVO> orderVoList = new ArrayList<>();
  for (OrderMaster orderMaster:orderMasterList){
    OrderVO orderVo = new OrderVO();
    BeanUtils.copyProperties(orderMaster,orderVo);
  orderVoList.add(orderVo);
  }
    System.out.println(orderVoList);
  return  orderVoList;
  }
  @GetMapping("/cancel")
  @ResponseBody
  public ResultVO cancel(@RequestParam("orderId") String orderId){
    try{
      OrderDTO orderDTO = orderService.findOne(orderId);
      OrderDTO orderDTO1 = orderService.cancel(orderDTO);

    }catch(SellException e){
      log.error("【卖家端取消订单】，查询不到订单{}",e);

      return new ResultVO(400,"未查找到订单");
    }
  return new ResultVO(200,"订单已取消");


  }
  @GetMapping("/detail")
  @ResponseBody
  public OrderDTO detail(@RequestParam("orderId")  String orderId,HttpServletResponse response){
    OrderDTO orderDTO = new OrderDTO();
    try {
      orderDTO = orderService.findOne(orderId);
    }catch (SellException e){
      log.error("【卖家端订单详情】，查询不到订单{}",e);
      response.setStatus(500);
      return orderDTO;
    }
    return  orderDTO;
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
