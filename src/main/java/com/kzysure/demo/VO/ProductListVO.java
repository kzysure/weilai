package com.kzysure.demo.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kzysure.demo.utils.serializer.Date2LongSerializer;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public class ProductListVO {
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
  /**
   * 类名
   */
  private String cateType;

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public BigDecimal getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(BigDecimal productPrice) {
    this.productPrice = productPrice;
  }

  public Integer getProductStock() {
    return productStock;
  }

  public void setProductStock(Integer productStock) {
    this.productStock = productStock;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public String getProductIcon() {
    return productIcon;
  }

  public void setProductIcon(String productIcon) {
    this.productIcon = productIcon;
  }

  public Integer getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(Integer productStatus) {
    this.productStatus = productStatus;
  }

  public Integer getCategoryType() {
    return categoryType;
  }

  public void setCategoryType(Integer categoryType) {
    this.categoryType = categoryType;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getCateType() {
    return cateType;
  }

  public void setCateType(String cateType) {
    this.cateType = cateType;
  }
}
