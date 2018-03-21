package com.kzysure.demo.enums;

import lombok.Getter;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
public enum PayStatusEnum {
  WAIT(0,"未支付"),
  SUCCESS(1,"支付成功");
  private Integer code;
  private String msg;

  PayStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
