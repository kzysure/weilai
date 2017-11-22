package com.imooc.demo.repository;

import com.imooc.demo.dataobject.OrderDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
  List<OrderDetail> findByOrderId(String orderId);
}
