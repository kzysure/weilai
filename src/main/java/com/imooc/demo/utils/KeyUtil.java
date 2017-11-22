package com.imooc.demo.utils;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */

import java.util.Random;

/**生成唯一主键 */
public class KeyUtil {
  public static synchronized String genUniqueKey(){
    Random random = new Random();
    Integer number = random.nextInt(9000000)+1000000;
    return System.currentTimeMillis() + String.valueOf(number);
  }

}
