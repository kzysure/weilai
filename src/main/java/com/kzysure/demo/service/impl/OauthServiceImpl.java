package com.kzysure.demo.service.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.net.HttpHeaders;
import com.google.gson.JsonObject;
import com.kzysure.demo.service.OauthService;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class OauthServiceImpl implements OauthService {

  @Override
  public Map<String,Object> getGithubUserInfo(String url) {
    RestTemplate restTemplate = new RestTemplate();
    Map<String,Object> map =restTemplate.getForObject(url,Map.class);
    System.out.println(map);
    return map;

  }

  @Override
  public String getGithubAccessToken(String url) {
    StringBuffer buffer = new StringBuffer();
    try {
      URL tourl = new URL(url);
      HttpURLConnection httpUrlConn = (HttpURLConnection) tourl.openConnection();

      httpUrlConn.setDoOutput(false);
      httpUrlConn.setDoInput(true);
      httpUrlConn.setUseCaches(false);

      httpUrlConn.setRequestMethod("GET");
      httpUrlConn.connect();
      // 将返回的输入流转换成字符串
      InputStream inputStream = httpUrlConn.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str);
      }
      //res = new String(buffer.toString().getBytes("iso-8859-1"),"utf-8");
      bufferedReader.close();
      inputStreamReader.close();
      // 释放资源
      inputStream.close();
      inputStream = null;
      httpUrlConn.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }
}
