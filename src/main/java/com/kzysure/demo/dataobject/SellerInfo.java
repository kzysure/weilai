package com.kzysure.demo.dataobject;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
public class SellerInfo {
  @Id
private String id;
  private String username;
  private String password;
  private String openid;
  private Date createTime;
  private Date updateTime;



}
