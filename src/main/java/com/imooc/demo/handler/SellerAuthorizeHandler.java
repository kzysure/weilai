package com.imooc.demo.handler;

import com.imooc.demo.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@ControllerAdvice
public class SellerAuthorizeHandler {
  @ExceptionHandler(value = SellerAuthorizeException.class)
  public ModelAndView handlerAuthorizeException(){
    return new ModelAndView("redirect:/seller/loginto");
  }


}
