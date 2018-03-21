package com.kzysure.demo.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class WechatMpConfig {
  @Autowired WechatAccountConfig accountConfig;
  @Bean
  public WxMpService wxMpService(){
    WxMpService wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
    return wxMpService;
  }
  @Bean
  public WxMpConfigStorage wxMpConfigStorage(){
    WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
    wxMpInMemoryConfigStorage.setAppId(accountConfig.getMpAppId());
    wxMpInMemoryConfigStorage.setSecret(accountConfig.getMpAppSecret());
    return wxMpInMemoryConfigStorage;
  }

}
