package com.imooc.demo.service;

import com.imooc.demo.dataobject.SellerInfo;

/**
 * 卖家服务
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */

/**
 * 通过openId查询卖家信息
 * @param openid
 * @return
 */
public interface SellerService {

  SellerInfo findSellerByOpenid(String openid);

}
