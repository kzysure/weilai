package com.kzysure.demo.service.impl;

import com.kzysure.demo.dataobject.ProductInfo;
import com.kzysure.demo.dto.CartDTO;
import com.kzysure.demo.enums.ProductStatusEnum;
import com.kzysure.demo.enums.ResultEnums;
import com.kzysure.demo.exception.SellException;
import com.kzysure.demo.repository.ProductInfoRepository;
import com.kzysure.demo.service.ProductInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{
  @Autowired
  ProductInfoRepository productInfoRepository;
  @Override
  public ProductInfo findOne(String productId) {
    return productInfoRepository.findOne(productId);
  }

  @Override
  public List<ProductInfo> findUpAll() {
    return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
  }

  @Override
  public Page<ProductInfo> findAll(Pageable pageable) {
    return productInfoRepository.findAll(pageable);
  }


  @Override
  public ProductInfo save(ProductInfo productInfo) {
    return productInfoRepository.save(productInfo);
  }

  @Override
  public void increaseStock(List<CartDTO> cartDTOList) {
    for (CartDTO cartDTO: cartDTOList){
      ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
      if (productInfo==null){
        throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
      }
      Integer cartQuantity = productInfo.getProductStock()+ cartDTO.getProductQuantity();
      productInfo.setProductStock(cartQuantity);
      productInfoRepository.save(productInfo);

    }

  }

  @Override
  @Transactional
  public void decreaseStock(List<CartDTO> cartDTOList) {
    for (CartDTO cartDTO: cartDTOList){
      ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
      if (productInfo == null){
        throw  new SellException(ResultEnums.PRODUCT_NOT_EXIST);
      }
      Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
      if (result < 0){
        throw new SellException(ResultEnums.PRODUCT_STOCK_ERROR);
      }
      productInfo.setProductStock(result);
      productInfoRepository.save(productInfo);


    }

  }
}
