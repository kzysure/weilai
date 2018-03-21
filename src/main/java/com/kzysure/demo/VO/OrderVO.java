package com.kzysure.demo.VO;

import com.kzysure.demo.dataobject.OrderDetail;
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
public class OrderVO {
  private String orderId;
  private  String buyerName;
  private String buyerPhone;
  private  String buyerAddress;
  private String buyerOpenid;
  private BigDecimal orderAmount;
  private Integer orderStatus;
  private Integer payStatus;
  private Date createTime;
  private Date updateTime;
  private List<OrderDetail> orderDetailList;
}

