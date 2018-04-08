package com.kzysure.demo.repository;

import com.kzysure.demo.dataobject.OrderMaster;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{
  Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Pageable pageable);
  List<OrderMaster> findOrderMastersByBuyerOpenidOrderByCreateTimeDesc(String openid);
  List<OrderMaster> findAllByOrderByCreateTimeDesc();
}
