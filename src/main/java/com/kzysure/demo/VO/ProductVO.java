package com.kzysure.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ProductVO {
  @JsonProperty("name")
private String categoryName;
  @JsonProperty("type")
private Integer categoryType;
  @JsonProperty("foods")
private List<ProductInfoVO> productInfoVOList;
}
