package com.kzysure.demo.constant;

import lombok.Data;

/**
 * Redis常量
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RedisConfig {
  String TOKEN_PREFIX ="token_%s";
  Integer EXPIRE =7200; //两小时
}
