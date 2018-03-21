package com.kzysure.demo.controller;

import com.kzysure.demo.VO.LoginResponse;
import com.kzysure.demo.config.GithubOauthConfig;
import com.kzysure.demo.constant.RedisConfig;
import com.kzysure.demo.dataobject.SellerInfo;
import com.kzysure.demo.repository.SellerInfoRepository;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@CrossOrigin
@RequestMapping("/seller")
public class SellerLoginController {
  @Autowired
  GithubOauthConfig githubOauthConfig;
  @Autowired
  SellerInfoRepository sellerInfoRepository;
  @Autowired
  StringRedisTemplate redisTemplate;
  @GetMapping("/loginto")
public ModelAndView login(Map<String,Object> map){
    String url = githubOauthConfig.getUrl()+"client_id="+githubOauthConfig.getClientId()+"&state="
        +githubOauthConfig.getState()+"&redirect_uri="+githubOauthConfig.getRedirectUrl();
    map.put("url",url);
  return new ModelAndView("login/login",map);
}
@GetMapping("/denglu")
@ResponseBody
public LoginResponse denglu(@RequestParam String username,@RequestParam String password){
  SellerInfo sellerInfo = sellerInfoRepository.findByUsernameAndPassword(username,password);
  if (sellerInfo!=null){
    String token = UUID.randomUUID().toString();
    //设置token到redis
    Integer expire = RedisConfig.EXPIRE;
    redisTemplate.opsForValue().set(String.format(RedisConfig.TOKEN_PREFIX,token),sellerInfo.getId(),expire,
        TimeUnit.SECONDS);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setMsg("登录成功");
    loginResponse.setUsername(sellerInfo.getUsername());
    loginResponse.setOpenid(sellerInfo.getOpenid());
    loginResponse.setToken(token);
    loginResponse.setSuccess(true);
    System.out.println("login success");
    return loginResponse;

  }else{
    LoginResponse loginResponse = new LoginResponse(false,"登录失败");
    return loginResponse;

  }
}
}
