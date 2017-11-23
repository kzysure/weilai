package com.imooc.demo.controller;

import com.imooc.demo.config.GithubOauthConfig;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@RequestMapping("/seller")
public class SellerLoginController {
  @Autowired
  GithubOauthConfig githubOauthConfig;
  @GetMapping("/loginto")
public ModelAndView login(Map<String,Object> map){
    String url = githubOauthConfig.getUrl()+"client_id="+githubOauthConfig.getClientId()+"&state="
        +githubOauthConfig.getState()+"&redirect_uri="+githubOauthConfig.getRedirectUrl();
    map.put("url",url);
  return new ModelAndView("login/login",map);
}
}
