package com.imooc.demo.controller;

import com.google.gson.JsonObject;
import com.imooc.demo.config.GithubOauthConfig;


import com.imooc.demo.dataobject.SellerInfo;
import com.imooc.demo.repository.SellerInfoRepository;
import com.imooc.demo.service.OrderService;
import com.imooc.demo.service.SellerService;
import com.imooc.demo.service.impl.OauthServiceImpl;

import com.imooc.demo.service.impl.SellerServiceImpl;
import java.lang.reflect.Type;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/oauth")
public class GithubOauth {
  @Autowired
  GithubOauthConfig githubOauthConfig;
  @Autowired
  OauthServiceImpl oauthService;
  @Autowired
  OrderService orderService;
  @Autowired
  SellerInfoRepository sellerInfoRepository;
@GetMapping("/githubOauth")
  public ModelAndView githubOauth(@RequestParam("code") String code,@RequestParam("state") String state,Map<String,String> map) {
  String url =
      githubOauthConfig.getAccessTokenUrl() + "client_id=" + githubOauthConfig.getClientId()
          + "&client_secret=" +
          githubOauthConfig.getClientSecret() + "&code=" + code + "&redirect_uri="
          + githubOauthConfig.getRedirectUrl();
 String buffer = oauthService.getGithubAccessToken(url);
 String apiInfo= githubOauthConfig.getApiInfo()+buffer.toString();
 Map<String,Object> map1 = oauthService.getGithubUserInfo(apiInfo);
//  System.out.println(map1.get("id"));
//  System.out.println(id);
 SellerInfo sellerInfo = sellerInfoRepository.findOne(map1.get("id").toString());

 if (sellerInfo == null){
   SellerInfo sellerInfo1 = new SellerInfo();
   sellerInfo1.setId(map1.get("id").toString());
   sellerInfo1.setUsername(map1.get("login").toString());
   sellerInfo1.setOpenid(map1.get("id").toString());
   sellerInfo1.setPassword(map1.get("id").toString());

   sellerInfoRepository.save(sellerInfo1);
 }

  //授权登陆成功之后
  map.put("url","/sell/seller/order/list");
  map.put("msg","github授权登陆成功");
  return new ModelAndView("common/success",map);
}

}


