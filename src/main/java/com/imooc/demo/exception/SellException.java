package com.imooc.demo.exception;

import com.imooc.demo.enums.ResultEnums;
import lombok.Getter;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
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
