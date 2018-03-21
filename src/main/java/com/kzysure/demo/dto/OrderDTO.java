package com.kzysure.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kzysure.demo.dataobject.OrderDetail;
import com.kzysure.demo.enums.OrderStatusEnum;
import com.kzysure.demo.enums.PayStatusEnum;
import com.kzysure.demo.utils.serializer.Date2LongSerializer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
//让null值的对象不反回，此处注释掉，使用全局设置，见application.yml
//@JsonInclude(Include.NON_NULL)
public class OrderDTO {
  /** 订单id.*/
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
  @JsonSerialize(using = Date2LongSerializer.class)
  private Date createTime;

  /**订单更新时间*/
//  时间转换注解
  @JsonSerialize(using = Date2LongSerializer.class)
  private Date updateTime;

private List<OrderDetail> orderDetailList;

}
