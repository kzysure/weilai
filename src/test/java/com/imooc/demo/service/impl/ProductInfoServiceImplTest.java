package com.imooc.demo.service.impl;

import static org.junit.Assert.*;

import com.imooc.demo.dataobject.ProductInfo;
import com.imooc.demo.enums.ProductStatusEnum;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.debugger.Page;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
@Autowired ProductInfoServiceImpl productInfoService;
  @Test
  public void findOne() throws Exception {
    ProductInfo productInfo = productInfoService.findOne("kzysure");
    System.out.println(productInfo.toString());
  }

  @Test
  public void findUpAll() throws Exception {
    List<ProductInfo> productInfo = productInfoService.findUpAll();
    System.out.println(productInfo.toString());
  }

  @Test
  public void findAll() throws Exception {
    PageRequest pageRequest = new PageRequest(0,2);
    org.springframework.data.domain.Page<ProductInfo> productInfos = productInfoService.findAll(pageRequest);
    System.out.println(productInfos.getTotalElements());
    Assert.assertNotNull(productInfos);
  }

  @Test
  public void save() throws Exception {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("猫星人的狗不理");
    productInfo.setProductName("伊莎贝拉");
    productInfo.setProductStock(998);
    productInfo.setProductPrice(new BigDecimal(532));
    productInfo.setProductIcon("包子");
    productInfo.setProductStatus(0);
    productInfo.setCategoryType(8);
    ProductInfo productInfo1 = productInfoService.save(productInfo);
    System.out.println(productInfo1.toString());
  }

}