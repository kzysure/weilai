package com.imooc.demo.repository;

import static org.junit.Assert.*;

import com.imooc.demo.dataobject.OrderDetail;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
  @Autowired OrderDetailRepository orderDetailRepository;

  @Test
  public void saveTest(){
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setDetailId("3");
    orderDetail.setOrderId("1");
    orderDetail.setProductName("黄焖鸡米饭");
    orderDetail.setProductPrice(new BigDecimal(520.0));
    orderDetail.setProductIcon("placehold.it/320x320");
    orderDetail.setProductId("yrl");
    orderDetail.setProductQuantity(32);
    orderDetailRepository.save(orderDetail);
  }
  @Test
  public void findByOrderId() throws Exception {
    List<OrderDetail> result = this.orderDetailRepository.findByOrderId("1");
    Assert.assertNotNull(result);
    Assert.assertNotEquals(0,result.size());
    System.out.println(result.toString());

  }

}