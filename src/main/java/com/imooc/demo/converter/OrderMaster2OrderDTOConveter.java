package com.imooc.demo.converter;

import com.imooc.demo.dataobject.OrderMaster;
import com.imooc.demo.dto.OrderDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class OrderMaster2OrderDTOConveter {
  public static OrderDTO conveter(OrderMaster orderMaster){
    OrderDTO orderDTO = new OrderDTO();
    BeanUtils.copyProperties(orderMaster,orderDTO);
    return orderDTO;
  }
  public static List<OrderDTO> conveter(List<OrderMaster> orderMasterList){
    List<OrderDTO> orderDTOList = new ArrayList<>();

    orderDTOList = orderMasterList.stream().map(e-> conveter(e)).collect(Collectors.toList());
    return orderDTOList;
  }

}
