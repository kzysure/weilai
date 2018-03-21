//package com.kzysure.demo.aspect;
//
//import javax.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
// * @version 1.0.0
// * @since 1.0.0
// */
//@Aspect
//@Component
//@Slf4j
//public class HttpAspect {
//  @Pointcut("execution(public * com.kzysure.demo.controller.SellerOrderController.*(..))")
//  public void login(){}
//  @Before("login()")
//  public void doBefore(HttpServletResponse httpServletResponse){
//
//
//  }
//
//}
