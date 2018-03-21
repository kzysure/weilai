package com.kzysure.demo.service.impl;

import com.kzysure.demo.dataobject.SellerInfo;
import com.kzysure.demo.repository.SellerInfoRepository;
import com.kzysure.demo.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@Slf4j
public class SellerServiceImpl implements SellerService{
@Autowired
  SellerInfoRepository sellerInfoRepository;
  @Override
  public SellerInfo findSellerByOpenid(String openid) {
    SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
    if (sellerInfo == null){
      log.error("【卖家服务】，卖家查询结果为空");
    }
    return sellerInfo;
  }
}
