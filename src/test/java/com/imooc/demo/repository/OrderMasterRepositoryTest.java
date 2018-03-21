package com.kzysure.demo.repository;

import static org.junit.Assert.*;

import com.kzysure.demo.dataobject.OrderMaster;
import com.kzysure.demo.enums.OrderStatusEnum;
import com.kzysure.demo.enums.PayStatusEnum;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
  private final String OPENID="yrl";
@Autowired OrderMasterRepository orderMasterRepository;
@Test
public void saveTest(){
  OrderMaster orderMaster = new OrderMaster();
  orderMaster.setOrderId("4");
  orderMaster.setBuyerName("yrl");
  orderMaster.setBuyerAddress("ECUT");
  orderMaster.setBuyerOpenid(OPENID);
  orderMaster.setBuyerPhone("18279114488");
//  orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
//  orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
  orderMaster.setOrderAmount(new BigDecimal(520));
  orderMasterRepository.save(orderMaster);
}
  @Test
  public void findByBuyerOpenid() throws Exception {
    PageRequest request = new PageRequest(1,3);
  OrderMaster orderMaster = new OrderMaster();
  Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("yrl",request);
    System.out.println(result.getTotalElements());
    Assert.assertNotEquals(0,result.getTotalElements());
  }

}