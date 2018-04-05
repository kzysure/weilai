package com.kzysure.demo.handler;

import com.kzysure.demo.exception.SellerAuthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@ControllerAdvice
public class SellerAuthorizeHandler {
  @ExceptionHandler(value = SellerAuthorizeException.class)
//  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseEntity handlerAuthorizeException(){
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }



}
