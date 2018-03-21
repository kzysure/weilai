package com.kzysure.demo.dto;

import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class CartDTO {
//  商品id
  private String productId;

//  数量
  private Integer productQuantity;

  public CartDTO(String productId, Integer productQuantity) {
    this.productId = productId;
    this.productQuantity = productQuantity;
  }
}
