package com.kzysure.demo.dataobject;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
//@DynamicUpdate
public class OrderDetail {
  /** 订单详情id. */
  @Id
  private String detailId;

  /** 订单详情id. */
  /** 订单id */
  private String orderId;
  /** 商品id. */
  private String productId;
  /** 商品名称. */
  private String productName;
  /** 商品价格.  */
  private BigDecimal productPrice;
  /** 商品库存. */
  private Integer productQuantity;
  /** 商品缩略图.*/
  private String productIcon;



}
