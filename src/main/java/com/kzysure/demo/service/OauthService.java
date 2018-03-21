package com.kzysure.demo.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import java.util.Map;

/**
 * 登陆相关服务
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface OauthService {
  String getGithubAccessToken(String code);
  Map<String,Object> getGithubUserInfo(String url);

}
