package com.imooc.demo.enums;

import lombok.Getter;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
public enum OrderStatusEnum {
  NEW(0,"新订单"),
  FINISHED(1,"已完成订单"),
  CANCEL(2,"已取消订单");
  private Integer code;
  private  String msg;

  OrderStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
