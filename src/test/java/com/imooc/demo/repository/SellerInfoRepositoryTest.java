package com.imooc.demo.repository;

import static org.junit.Assert.*;

import com.imooc.demo.dataobject.SellerInfo;
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
public class SellerInfoRepositoryTest {
@Autowired SellerInfoRepository sellerInfoRepository;
  @Test
  public void findByOpenid() throws Exception {
    SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("44");
    System.out.println(sellerInfo.getUsername());
    System.out.println(sellerInfo.toString());
    Assert.assertTrue("获取的SellerInfo不为空",!sellerInfo.equals(null));
  }
  @Test
  public void findOneTest(){
    SellerInfo sellerInfo = sellerInfoRepository.findOne("12");
    System.out.println(sellerInfo.toString());
  }

}