package com.kzysure.demo.controller;

import com.kzysure.demo.VO.ProductInfoVO;
import com.kzysure.demo.VO.ProductVO;
import com.kzysure.demo.VO.ResultVO;
import com.kzysure.demo.dataobject.ProductCategory;
import com.kzysure.demo.dataobject.ProductInfo;
import com.kzysure.demo.service.CategoryService;
import com.kzysure.demo.service.ProductInfoService;
import com.kzysure.demo.utils.ResultVoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
  @Autowired ProductInfoService productInfoService;
  @Autowired CategoryService categoryService;
  @GetMapping("/list")
  public ResultVO list(){
//    1.查询所有上架的商品
    List<ProductInfo> productInfoList = productInfoService.findUpAll();

//    2.查询所有类目
////    传统方法
//    List<Integer> categoryTypeList = new ArrayList<>();
//    for (ProductInfo productInfo: productInfoList){
//      categoryTypeList.add(productInfo.getCategoryType());
//    }
//    Java8特性,lamda表达式
    List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(
        Collectors.toList());
  List<ProductCategory> productCategory = categoryService.findByCategoryTypeIn(categoryTypeList);
//3.数据拼装
    List<ProductVO> productVOList = new ArrayList<>();
for (ProductCategory productCategory1 : productCategory){
 ProductVO productVO = new ProductVO();
 productVO.setCategoryName(productCategory1.getCategoryName());
 productVO.setCategoryType(productCategory1.getCategoryType());
List<ProductInfoVO> productInfoVOList = new ArrayList<>();
 for (ProductInfo productInfo: productInfoList){
   if (productCategory1.getCategoryType().equals(productInfo.getCategoryType())){
     ProductInfoVO productInfoVO = new ProductInfoVO();
     BeanUtils.copyProperties(productInfo,productInfoVO);
     productInfoVOList.add(productInfoVO);
   }

 }
 productVO.setProductInfoVOList(productInfoVOList);
 productVOList.add(productVO);
}
//    ResultVO resultVO = new ResultVO();
//    resultVO.setData(productVOList);
//    resultVO.setCode(0);
//    resultVO.setMsg("成功");
//    return  resultVO;
    return  ResultVoUtil.success(productVOList);
//    return ResultVoUtil.success();
//    return ResultVoUtil.error(1,"请求出了问题");
  }

}
