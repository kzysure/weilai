package com.imooc.demo.exception;

import com.imooc.demo.enums.ResultEnums;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class SellException extends RuntimeException {
private Integer code;

  public SellException(ResultEnums resultEnums) {
    super(resultEnums.getMsg());
    this.code = resultEnums.getCode();
  }

  public SellException(Integer code,String msg) {
    super(msg);
    this.code = code;

  }
}
