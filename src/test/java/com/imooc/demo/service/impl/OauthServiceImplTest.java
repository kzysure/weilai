package com.kzysure.demo.service.impl;

import static org.junit.Assert.*;

import com.google.gson.JsonObject;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthServiceImplTest {
@Autowired OauthServiceImpl oauthService;
  @Test
  public void getGithubUserInfo() throws Exception {
    Map<String,Object> map = oauthService.getGithubUserInfo("");
  }

  @Test
  public void getGithubAccessToken() throws Exception {
    Map<String,Object> map = oauthService.getGithubUserInfo("https://api.github.com/user?access_token=bd404c9ea5fa1bc3bde27b3dac664d9632c08342&scope=&token_type=bearer");
    System.out.println(map.toString());
  }

}