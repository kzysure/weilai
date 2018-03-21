package com.kzysure.demo.service;

import com.kzysure.demo.dataobject.ProductInfo;
import com.kzysure.demo.dto.CartDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProductInfoService {
  ProductInfo findOne(String productId);

  List<ProductInfo> findUpAll();

  Page<ProductInfo> findAll(Pageable pageable);

  ProductInfo save( ProductInfo productInfo);

  //加库存
  void increaseStock(List<CartDTO> cartDTOList);

  //加库存
  void decreaseStock(List<CartDTO> cartDTOList);
}
