package com.kzysure.demo.dataobject;

import com.kzysure.demo.enums.OrderStatusEnum;
import com.kzysure.demo.enums.PayStatusEnum;
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
@DynamicUpdate
public class OrderMaster {
  /** 订单id.*/
  @Id
  private String orderId;

  /**买家名字.*/
  private String buyerName;

  /**买家联系方式.*/
  private String buyerPhone;

  /**买家地址.*/
  private String buyerAddress;

  /**买家微信openId.*/
  private String buyerOpenid;

  /**订单总额.*/
  private BigDecimal orderAmount;

  /**订单状态，默认为新下单.*/
  private Integer orderStatus = OrderStatusEnum.NEW.getCode();

  /**支付状态,默认为0，未支付.*/
  private Integer payStatus = PayStatusEnum.WAIT.getCode();

  /**订单创建时间.*/
  private Date createTime;

  /**订单更新时间*/
  private Date updateTime;

}
