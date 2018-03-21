package com.kzysure.demo.VO;

import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class LoginResponse {
  private Boolean success;
  private String token;
  private String username;
  private String openid;
  private String msg;

  public LoginResponse() {
  }

  public LoginResponse(Boolean success, String msg) {
    this.success = success;
    this.msg = msg;

  }
}
