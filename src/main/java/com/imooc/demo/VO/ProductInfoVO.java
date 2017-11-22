package com.imooc.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ProductInfoVO {
  @JsonProperty("id")
  private String productId;
  @JsonProperty("name")
  private String productName;
  @JsonProperty("price")
  private BigDecimal productPrice;
  @JsonProperty("description")
  private String productDescription;
  @JsonProperty("icon")
  private String productIcon;

}
