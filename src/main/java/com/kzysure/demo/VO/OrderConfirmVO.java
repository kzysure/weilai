package com.kzysure.demo.VO;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class OrderConfirmVO {
private String productName;
private  Integer productQuantity;
private BigDecimal orderAmount;
private String productIcon;
}
