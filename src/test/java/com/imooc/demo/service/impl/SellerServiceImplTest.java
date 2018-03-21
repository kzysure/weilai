package com.kzysure.demo.service.impl;

import static org.junit.Assert.*;

import com.kzysure.demo.dataobject.SellerInfo;
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
public class SellerServiceImplTest {
@Autowired SellerServiceImpl sellerService;
  @Test
  public void findSellerByOpenid() throws Exception {
    SellerInfo sellerInfo = sellerService.findSellerByOpenid("44");
    Assert.assertTrue("sellerInfo不为空",!sellerInfo.equals(null));
    Assert.assertEquals("44",sellerInfo.getOpenid());
  }

}