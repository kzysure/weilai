package com.imooc.demo.repository;

import static org.junit.Assert.*;

import com.imooc.demo.dataobject.ProductInfo;
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
public class ProductInfoRepositoryTest {
@Autowired
ProductInfoRepository productInfoRepository;
@Test
  public void findOne(){
  ProductInfo productInfo = productInfoRepository.findOne("kyzsure");
//  System.out.println(productInfo.getProductDescription());

}
  @Test
  public void findByProductStatus() throws Exception {
    List<ProductInfo> productInfo = productInfoRepository.findByProductStatus(1);
    System.out.println(productInfo.size());
    System.out.println(productInfo.toString());
  }
  @Test
  public void save(){
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("yrld");
    productInfo.setProductName("喵喵");
    productInfo.setProductDescription("可爱的喵喵");
    productInfo.setCategoryType(2);
    productInfo.setProductStatus(1);
    productInfo.setProductIcon("icon");
    productInfo.setProductPrice(new BigDecimal(520));
    productInfo.setProductStock(100);
    ProductInfo productInfo1 = productInfoRepository.save(productInfo);
    Assert.assertNotNull(productInfo1);


  }
  @Test
  public void findAll(){
    List<ProductInfo> productInfo = productInfoRepository.findAll();
    System.out.println(productInfo.toString());
  }
  @Test
  public void update(){
    ProductInfo productInfo = productInfoRepository.findOne("yrl");
    productInfo.setProductName("我老婆");
    ProductInfo productInfo1 = productInfoRepository.save(productInfo);
    System.out.println(productInfo1.toString());
  }
  @Test
  public void remove(){
    productInfoRepository.delete("yrld");
  }


}