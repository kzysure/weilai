package com.imooc.demo.controller;

import com.imooc.demo.VO.ResultVO;
import com.imooc.demo.converter.OrderForm2OrderDTOConverter;
import com.imooc.demo.dataobject.OrderMaster;
import com.imooc.demo.dto.OrderDTO;
import com.imooc.demo.enums.ResultEnums;
import com.imooc.demo.exception.SellException;
import com.imooc.demo.form.ListForm;
import com.imooc.demo.form.OrderForm;
import com.imooc.demo.service.BuyerService;
import com.imooc.demo.service.OrderService;
import com.imooc.demo.utils.ResultVoUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
  @Autowired
  OrderService orderService;
  @Autowired
  BuyerService buyerService;
  //创建商品
  @PostMapping("/create")
  public ResultVO<Map<String,String>> createOrder(@Valid OrderForm orderForm,BindingResult bindingResult){
    if (bindingResult.hasErrors()){
      log.error("【创建订单】参数不正确，orderForm：{}",orderForm);
      throw new SellException(ResultEnums.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
    }
    OrderForm2OrderDTOConverter orderForm2OrderDTOConverter = new OrderForm2OrderDTOConverter();
    OrderDTO orderDTO = orderForm2OrderDTOConverter.orderForm2orderDTOConverter(orderForm);
    if (orderDTO.getOrderDetailList()==null){
      log.error("购物车不能为空");
      throw new SellException(ResultEnums.PARAM_ERROR);
    }
    OrderDTO orderDTO1 = orderService.createOrder(orderDTO);
    if (orderDTO1==null){
      log.error("创建订单失败");
      throw new SellException(ResultEnums.CREATE_ORDER_ERROR);
    }
Map<String,String> map = new HashMap<>();
    map.put("orderId",orderDTO1.getOrderId());
    return ResultVoUtil.success(map);
  }
  @GetMapping("/list")
  public ResultVO<List<OrderDTO>> findList(ListForm listForm){
    if (listForm.getOpenid()==null){
      log.error("【查询订单】,Openid为空");
      throw new SellException(ResultEnums.PARAM_ERROR);
    }
    PageRequest pageRequest = new PageRequest(listForm.getPage(),listForm.getSize());
    Page<OrderDTO> orderDTOPage = orderService.findList(listForm.getOpenid(),pageRequest);
return ResultVoUtil.success(orderDTOPage.getContent());
  }
  @GetMapping("/detail")
  public ResultVO<OrderDTO> finOne(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
      OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
      return ResultVoUtil.success(orderDTO);
  }
  @PostMapping("/cancel")
  public ResultVO cancelOrder(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
    OrderDTO orderDTO = buyerService.cancelOrder(openid,orderId);
    return ResultVoUtil.success();
  }
}
