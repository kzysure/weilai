package com.imooc.demo.SellerAuthorizeAspect;

import com.imooc.demo.constant.RedisConfig;
import com.imooc.demo.exception.SellerAuthorizeException;
import com.imooc.demo.utils.CookieUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Pointcut("execution(public * com.imooc.demo.controller.Seller*.*(..))"+"&& !execution(public * com.imooc.demo.controller.SellerUserController.*(..))"+"&&!execution(public * com.imooc.demo.controller.SellerLoginController.*(..))")
//  @Pointcut("execution(public * com.imooc.demo.controller.SellerOrderController.*(..))")
  public void verify(){}
  @Before("verify()")
  public void doVerify(){
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = servletRequestAttributes.getRequest();
    //查询cookie
    Cookie cookie = CookieUtil.get(request,"token");
    if (cookie == null){
      log.warn("【登陆校验】，Cookie中查不到token");
      throw new SellerAuthorizeException();
    }
    //去redis里查token
    String redisTOken = stringRedisTemplate.opsForValue().get(String.format(RedisConfig.TOKEN_PREFIX,cookie.getValue()));
      if (StringUtils.isEmpty(redisTOken)){
        log.warn("【登陆校验】，redis查不到token");
        throw new SellerAuthorizeException();
      }
  }
}
