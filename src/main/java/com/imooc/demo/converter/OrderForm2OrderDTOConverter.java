package com.imooc.demo.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.demo.dataobject.OrderDetail;
import com.imooc.demo.dto.OrderDTO;
import com.imooc.demo.enums.ResultEnums;
import com.imooc.demo.exception.SellException;
import com.imooc.demo.form.OrderForm;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
  public OrderDTO orderForm2orderDTOConverter(OrderForm orderForm){
    Gson gson = new Gson();
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName(orderForm.getName());
    orderDTO.setBuyerPhone(orderForm.getPhone());
    orderDTO.setBuyerAddress(orderForm.getAddress());
    orderDTO.setBuyerOpenid(orderForm.getOpenid());
    List<OrderDetail> orderDetailList;
    try{
      orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());

    }catch(Exception e){
      log.error("【对象转换异常】,string={}",orderForm.getItems());
      throw new SellException(ResultEnums.PARAM_ERROR);
    }
    orderDTO.setOrderDetailList(orderDetailList);
    return orderDTO;

  }

}
