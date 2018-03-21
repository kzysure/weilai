package com.kzysure.demo.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kzysure.demo.utils.serializer.Date2LongSerializer;
import java.math.BigDecimal;
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
public class ProductInfo {
  @Id
  private String productId;

  /** 名字 */
  private String productName;

  /** 单价 */
  private BigDecimal productPrice;

  /** 库存 */
  private Integer productStock;

  /** 商品描述 */
  private  String productDescription;

  /** 商品头像 */
  private String productIcon;

  /** 商品状态 */
  private Integer productStatus;

  /** 类别 */
  private Integer categoryType;
  /**订单创建时间.*/
  @JsonSerialize(using = Date2LongSerializer.class)
  private Date createTime;

  /**订单更新时间*/
//  时间转换注解
  @JsonSerialize(using = Date2LongSerializer.class)
  private Date updateTime;



}
