package com.imooc.demo.controller;

import com.imooc.demo.constant.RedisConfig;
import com.imooc.demo.dataobject.SellerInfo;
import com.imooc.demo.enums.ResultEnums;
import com.imooc.demo.exception.SellException;
import com.imooc.demo.service.SellerService;
import com.imooc.demo.utils.CookieUtil;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
  @Autowired
  SellerService sellerService;
  @Autowired
  StringRedisTemplate redisTemplate;
  @GetMapping("/login")
  public ModelAndView login(@RequestParam("openid") String openid,Map<String,String> map,HttpServletResponse httpServletResponse){
    SellerInfo sellerInfo = sellerService.findSellerByOpenid(openid);
    //和数据库匹配
    if (sellerInfo == null){
      map.put("msg", ResultEnums.LOGIN_ERROR.getMsg());
      map.put("url","/sell/seller/loginto");


      return new ModelAndView("common/error",map);
    }
    String token = UUID.randomUUID().toString();
    //设置token到redis

    Integer expire = RedisConfig.EXPIRE;
    redisTemplate.opsForValue().set(String.format(RedisConfig.TOKEN_PREFIX,token),openid,expire,
        TimeUnit.SECONDS);

    //设置token到cookies
    CookieUtil.set(httpServletResponse,"token",token,expire);

    return new ModelAndView("redirect:/seller/order/list");
  }
  @GetMapping("/logout")
  public ModelAndView logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Map<String,String> map){
    //从cookie里查询
    Cookie cookie = CookieUtil.get(httpServletRequest,"token");
    if (cookie != null){
      //清除redis

      redisTemplate.opsForValue().getOperations().delete(String.format(RedisConfig.TOKEN_PREFIX,cookie.getValue()));
    }
    //清除cookie
    CookieUtil.set(httpServletResponse,"token",null,0);
    map.put("msg",ResultEnums.LOGOUT_SUCCESS.getMsg());
    map.put("url","/sell/seller/loginto");
    return new ModelAndView("common/success",map);
  }

}
