package com.imooc.demo.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;

/**
 * cookie工具类
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class CookieUtil {

  /**
   * 设置cookie
   * @param httpServletResponse
   * @param name
   * @param value
   * @param maxAge
   */
  public static void set(HttpServletResponse httpServletResponse,String name,String value,int maxAge){
    Cookie cookie = new Cookie(name,value);
    cookie.setMaxAge(maxAge);
    cookie.setPath("/");
    httpServletResponse.addCookie(cookie);
  }

  /**
   * 获取cookies
   * @param httpServletRequest
   * @param name
   * @return
   */
  public static Cookie get(HttpServletRequest httpServletRequest,String name){
    Map<String,Cookie> map = readCookieMap(httpServletRequest);
    if (map.containsKey(name)){
      return map.get(name);
    }
    return null;
  }
  private static Map<String,Cookie> readCookieMap(HttpServletRequest httpServletRequest){
    Map<String,Cookie> map = new HashMap<>();
    Cookie[] cookies = httpServletRequest.getCookies();
    if (cookies != null){
      for (Cookie cookie: cookies){
        map.put(cookie.getName(),cookie);

      }
    }
    return map;
  }

}
