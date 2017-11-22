package com.imooc.demo.enums;

import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.Getter;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
public enum ProductStatusEnum {
  UP(0,"上架"),
  DOWN(1,"下架");
  private  Integer code;
  private String message;
  ProductStatusEnum(Integer code, String message) {
    this.code =code;
    this.message =message;
  }
}
