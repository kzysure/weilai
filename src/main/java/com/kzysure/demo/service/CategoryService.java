package com.kzysure.demo.service;

import com.kzysure.demo.dataobject.ProductCategory;

import java.util.List;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CategoryService {
  ProductCategory findOne(Integer categoryId);

  List<ProductCategory> findAll();

  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

  ProductCategory save(ProductCategory productCategory);

}
