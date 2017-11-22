package com.imooc.demo.dataobject;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
  public ProductCategory() {
  }

  //  类目ID
  @Id
  @GeneratedValue
  private Integer categoryId;
  //类目名字
  private String categoryName;
  //类目编号
  private Integer categoryType;


  //创建时间
  private Date createTime;
  //修改时间
  private Date updateTime;

}
