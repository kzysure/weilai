package com.imooc.demo.VO;

import lombok.Data;

/**
 * 返回给前端的最外层对象.
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ResultVO<T> {
private Integer code;
private String msg;
private T data;
}
