package com.kzysure.demo.repository;

import static org.junit.Assert.*;

import com.kzysure.demo.dataobject.ProductCategory;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductCategoryRepositoryTest {
  @Autowired ProductCategoryRepository productCategoryRepository;
  @Test
  public void findOneTest(){
   ProductCategory productCategory = productCategoryRepository.findOne(1);
   productCategory.setCategoryName("人气榜");
   productCategory.setCategoryType(3);
    System.out.println(productCategory.toString());
  }
  @Test
  @Transactional
  public void saveTest(){
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryName("女生!最爱");
    productCategory.setCategoryType(3);
    ProductCategory result = productCategoryRepository.save(productCategory);
//    Assert.assertNotNull(result);
    Assert.assertNotEquals(null,result);

  }
  @Test
  public void updateTest(){
    ProductCategory productCategory = productCategoryRepository.findOne(2);
    productCategory.setCategoryName("男生i9最爱");
    productCategory.setCategoryType(3);
    productCategoryRepository.save(productCategory);
  }
  @Test
  public void findByProductCategoryInTest(){
    List<Integer> list = Arrays.asList(2,3,4);
    List<ProductCategory> productCategory = productCategoryRepository.findByCategoryTypeIn(list);

    Assert.assertNotEquals(0,productCategory);

  }

}