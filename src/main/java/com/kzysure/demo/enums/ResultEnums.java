package com.kzysure.demo.enums;

import lombok.Getter;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
public enum ResultEnums {
  SUCCESS(0,"成功"),
  PARAM_ERROR(1,"参数不正确"),
  CREATE_ORDER_ERROR(2,"创建订单失败"),
  REQUEST_FORBIDEN(3,"非法请求"),
  PRODUCT_NOT_EXIST(10,"商品不存在"),
  PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
  ORDER_NOT_EXIST(12,"订单不存在"),
  ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
  ORDER_STATUS_ERROR(14,"订单状态异常"),
  ORDER_CANCEL_FAILD(15,"取消订单失败"),
  ORDER_DETAIL_EMPTY(16,"订单中无商品"),
  ORDER_UPDATE_FAIL(17,"订单更新失败"),
  ORDER_PAID_FAIL(18,"订单支付完成操作失败"),
  ORDER_OWNER_ERROR(19,"订单详情不允许被访问"),
  WECHAT_MP_ERROR(20,"微信公众账号错误"),
  ORDER_CANCEL_SUCCESS(21,"订单取消成功"),
  LOGIN_ERROR(22,"登陆失败"),
  LOGOUT_SUCCESS(23,"登出成功"),
  UPLOAD_SUCCESS(24,"上传成功"),
  UPLOAD_ERROR(25,"上传失败"),

  ;
  private Integer code;
  private String msg;

  ResultEnums(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
