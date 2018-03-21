package com.kzysure.demo.form;

import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class OrderForm {
  @NotEmpty(message = "买家名字不能为空")
  private String name;
  @NotEmpty(message = "买家联系方式不能为空")
  private String phone;
  @NotEmpty(message = "买家收货地址不能为空")
  private String address;
  @NotEmpty(message = "买家Openid不能为空")
  private String openid;
  @NotEmpty(message = "买家购物车不能为空")
  private String items;

}
