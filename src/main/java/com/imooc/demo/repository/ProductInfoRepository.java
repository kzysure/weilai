package com.imooc.demo.repository;

import com.imooc.demo.dataobject.ProductInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
  //查询上架商品
  List<ProductInfo> findByProductStatus(Integer productStatus);

}
