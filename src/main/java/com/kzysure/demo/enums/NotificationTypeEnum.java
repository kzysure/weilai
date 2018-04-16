package com.kzysure.demo.enums;

import lombok.Getter;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
public enum NotificationTypeEnum {
  INFO(0,"info"),
  SUCCESS(1,"success"),
  ERROR(2,"error"),
  WARNING(3,"warning"),
  BLANK(4,"blank");
  private Integer key;
  private  String value;

  NotificationTypeEnum(Integer key,String value) {
    this.key =key;
    this.value = value;
  }
}
