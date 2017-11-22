package com.imooc.demo.service.impl;

import com.imooc.demo.dataobject.ProductCategory;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
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
public class CategoryServiceImplTest {
  @Autowired
  CategoryServiceImpl categoryService;
  @Test
  public void findOne() throws Exception {
    ProductCategory productCategory = categoryService.findOne(1);
    Assert.assertEquals(new Integer(1),productCategory.getCategoryId());

  }

  @Test
  public void findAll() throws Exception {
    List<ProductCategory> productCategory = categoryService.findAll();
    Assert.assertNotEquals(0,productCategory.size());
  }

  @Test
  public void findByCategoryTypeIn() throws Exception {
    List<Integer> list = Arrays.asList(2,3,4);
    List<ProductCategory> productCategory = categoryService.findByCategoryTypeIn(list);
//    Assert.assertNotEquals(0,productCategory.size());
  }

  @Test
  public void save() throws Exception {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryName("彩虹糖");
    productCategory.setCategoryType(6);
    ProductCategory productCategory1 =categoryService.save(productCategory);
    Assert.assertNotNull(productCategory1);
  }

}