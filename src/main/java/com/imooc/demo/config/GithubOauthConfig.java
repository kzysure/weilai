package com.imooc.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "github")
public class GithubOauthConfig {
private String url;
private String redirectUrl;
private String clientId;
private String clientSecret;
private String state;
private String accessTokenUrl;
private String apiInfo;

}
