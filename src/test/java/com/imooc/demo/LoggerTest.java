package com.kzysure.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
//  private Logger logger = LoggerFactory.getLogger(LoggerTest.class); 换一种简单写法
  @Test
  public void loggerTest1(){
    String name ="kzysure";
    Integer age =18;
    log.error("错误");
    log.info("info");
    log.warn("警告");
//    log.warn("wo的名字叫{},今年{}岁了。",name,age);
  }

}
