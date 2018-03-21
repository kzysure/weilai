package com.kzysure.demo.service.impl;
import com.kzysure.demo.dataobject.ProductCategory;
import com.kzysure.demo.repository.ProductCategoryRepository;
import com.kzysure.demo.service.CategoryService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private ProductCategoryRepository categoryRepository;
  @Override
  public ProductCategory findOne(Integer categoryId) {
    return categoryRepository.findOne(categoryId);
  }

  @Override
  public List<ProductCategory> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
  return categoryRepository.findByCategoryTypeIn(categoryTypeList);
  }

  @Override
  public ProductCategory save(ProductCategory productCategory) {
    return categoryRepository.save(productCategory);
  }
}
