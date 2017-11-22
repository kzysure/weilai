package com.imooc.demo.service.impl;

import static org.junit.Assert.*;

import com.imooc.demo.dataobject.OrderDetail;
import com.imooc.demo.dataobject.OrderMaster;
import com.imooc.demo.dto.OrderDTO;
import com.imooc.demo.enums.OrderStatusEnum;
import com.imooc.demo.enums.PayStatusEnum;
import com.imooc.demo.repository.OrderDetailRepository;
import com.imooc.demo.repository.OrderMasterRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
  private static String orderId="15108231390359033763";
@Autowired OrderServiceImpl orderService;
@Autowired
  OrderMasterRepository orderMasterRepository;
@Autowired
  OrderDetailRepository orderDetailRepository;
private final String BUYEROPENID = "yrl";
  @Test
  public void createOrder() throws Exception {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName("喵星人");
    orderDTO.setBuyerAddress("上高");
    orderDTO.setBuyerPhone("18888888888");
    orderDTO.setBuyerOpenid(BUYEROPENID);
    List<OrderDetail> orderDetailList = new ArrayList<>();
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setProductId("yrl");
    orderDetail.setProductQuantity(2);
    orderDetailList.add(orderDetail);
    OrderDetail orderDetail1 = new OrderDetail();
    orderDetail1.setProductId("kzysure");
    orderDetail1.setProductQuantity(2);
    orderDetailList.add(orderDetail1);
    orderDTO.setOrderDetailList(orderDetailList);
    OrderDTO result = orderService.createOrder(orderDTO);
    log.info("【创建订单】result={}",result);
    Assert.assertNotNull(result);

  }
  @Test
  public void findOneTest(){
    OrderDTO orderDTO = orderService.findOne("15107398777294326201");
    Assert.assertNotNull(orderDTO);
    log.info("【查询订单】result={}",orderDTO);
  }
  @Test
  public void findList(){
  PageRequest pageRequest = new PageRequest(1,2);
  Page<OrderDTO> orderDTOPage = orderService.findList("yrl",pageRequest);
  Assert.assertNotNull(orderDTOPage.getContent());
  log.info("数据传输层数据：{}",orderDTOPage.getContent());
}
@Test
@Transactional
  public void OrderCancel(){
    OrderDTO orderDTO = new OrderDTO();
  OrderMaster orderMaster = orderMasterRepository.findOne("15108010687697562762");
  BeanUtils.copyProperties(orderMaster,orderDTO);
  List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("15108010687697562762");
  orderDTO.setOrderDetailList(orderDetailList);
  orderService.cancel(orderDTO);





}
@Test
@Transactional
  public void finishOrder(){
  OrderDTO orderDTO =  orderService.findOne(orderId);
  OrderDTO orderDTO1 = orderService.finish(orderDTO);
  Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),orderDTO1.getOrderStatus());

}
@Test
@Transactional
  public void paidOrder(){
    OrderDTO orderDTO = orderService.findOne(orderId);
    OrderDTO orderDTO1 = orderService.paid(orderDTO);
    Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),orderDTO1.getPayStatus());
}
@Test
  public void findListTest(){
    PageRequest pageRequest = new PageRequest(0,2);
    Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
  System.out.println(orderDTOPage.getTotalElements());
//  Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
  //判断
  Assert.assertTrue("查询所有订单",orderDTOPage.getTotalElements() > 0);
}
}