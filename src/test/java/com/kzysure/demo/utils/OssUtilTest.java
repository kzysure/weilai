package com.kzysure.demo.utils;

import static org.junit.Assert.*;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.kzysure.demo.config.AliyunOosConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OssUtilTest {
  private static String ENDPOINT;
  //阿里云API的密钥Access Key ID
  private static String ACCESS_KEY_ID;
  //阿里云API的密钥Access Key Secret
  private static String ACCESS_KEY_SECRET;
@Autowired
  AliyunOosConfig aliyunOosConfig;
  private OssUtil ossUtil = null;
  private OSSClient client = null;
  private String bucketName = "weilai-takeout";
  @Before
  public void initOssUtil(){
    ossUtil = new OssUtil();
    client = OssUtil.getOSSClient(aliyunOosConfig.getEndpoint(),aliyunOosConfig.getAccessKeyId(),aliyunOosConfig.getAccessKeySecret());
  }
  @Test
  public void getOSSClient() throws Exception {
    String bucketName = "weilai-takeout";
    //创建bucket
    assertEquals(true, OssUtil.createBucket(client, bucketName));
  }

  @Test
  public void createBucket() throws Exception {

  }

  @Test
  public void deleteBucket() throws Exception {
    OssUtil.deleteBucket(client,bucketName);
  }

  @Test
  public void uploadObject2OSS() throws Exception {
//    /Users/kzysure/Desktop/csdn_cs_qr.png
    //上传文件
    String flilePathName = "/Users/kzysure/Desktop/csdn_cs_qr.png";
    File file = new File(flilePathName);
    String diskName = "datas/image/";
    String md5key = OssUtil.uploadObject2OSS(client, file, bucketName, diskName);
    log.info("上传后的文件MD5数字唯一签名:" + md5key);  //上传后的文件MD5数字唯一签名:A30B046A34EB326C4A3BBD784333B017
  }

  @Test
  public void getOSS2InputStream() throws Exception {
  }

  @Test
  public void deleteFile() throws Exception {
    OssUtil.deleteFile(client, bucketName, "datas/image/", "csdn_cs_qr.png");

  }

  @Test
  public void getContentType() throws Exception {
  }

}