package com.kzysure.demo.VO;

import lombok.Data;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class WebsocketVO {
  private Integer key;
  private String msgType;
  private String msg;

}
