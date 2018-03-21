package com.kzysure.demo.form;

import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ListForm {
  private String openid;
  private Integer page;
  private Integer size;

}
