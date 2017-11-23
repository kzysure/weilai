package com.imooc.demo.handler;

import com.imooc.demo.VO.ResultVO;
import com.imooc.demo.exception.SellException;
import com.imooc.demo.utils.ResultVoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@ControllerAdvice
public class SellerExceptionHandler {
  @ExceptionHandler(value = SellException.class)
  //设置返回的状态码
//  @ResponseStatus(HttpStatus.FORBIDDEN)

  @ResponseBody
  public ResultVO sellExceptionHandler(SellException e){

    return ResultVoUtil.error(e.getCode(),e.getMessage());
  }

}
