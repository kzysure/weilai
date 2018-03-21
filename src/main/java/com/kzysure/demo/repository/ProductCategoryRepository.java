package com.kzysure.demo.repository;

import com.kzysure.demo.dataobject.ProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

