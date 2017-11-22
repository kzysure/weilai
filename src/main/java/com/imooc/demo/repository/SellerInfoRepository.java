package com.imooc.demo.repository;

import com.imooc.demo.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
SellerInfo findByOpenid(String openid);
}
