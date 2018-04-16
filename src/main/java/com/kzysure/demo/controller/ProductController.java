package com.kzysure.demo.controller;

import com.kzysure.demo.VO.ProductListVO;
import com.kzysure.demo.VO.ResultVO;
import com.kzysure.demo.dataobject.ProductCategory;
import com.kzysure.demo.dataobject.ProductInfo;
import com.kzysure.demo.repository.ProductCategoryRepository;
import com.kzysure.demo.repository.ProductInfoRepository;
import com.kzysure.demo.repository.SellerInfoRepository;
import com.kzysure.demo.service.ProductInfoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@CrossOrigin
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class ProductController {
  @Autowired
  ProductInfoService productInfoService;
  @Autowired
  ProductInfoRepository productInfoRepository;
  @Autowired
  ProductCategoryRepository productCategoryRepository;
  @GetMapping("/list")
  @ResponseBody
  public List<ProductListVO> list(){
List<ProductListVO> productListVOList = new ArrayList<>();
  List<ProductInfo> productInfoList = productInfoRepository.findAllByOrderByCreateTimeDesc();
  for (ProductInfo productInfo:productInfoList){
    ProductCategory productCategory = productCategoryRepository.findProductCategoryByCategoryType(productInfo.getCategoryType());
    ProductListVO productListVO = new ProductListVO();
    BeanUtils.copyProperties(productInfo,productListVO);
    productListVO.setCateType(productCategory.getCategoryName());
    productListVOList.add(productListVO);
  }
return productListVOList;
  }

  /**
   * 商品下架
   * @param id
   * @return
   */
  @PostMapping("/removeShelves")
  @ResponseBody
  ResultVO removeShelves(@RequestParam("id") String id){
    ProductInfo productInfo = productInfoRepository.findOne(id);
    if (null!=productInfo){
      productInfo.setProductStatus(1);
      productInfoRepository.save(productInfo);
      return new ResultVO(200,"下架成功");
    }else{
      return new ResultVO(500,"无此商品");
    }

  }

  /**
   * 商品上架
   * @param id
   * @return
   */
  @PostMapping("/addShelves")
  @ResponseBody
  ResultVO addShelves(@RequestParam("id") String id){
    ProductInfo productInfo = productInfoRepository.findOne(id);
    if (null!=productInfo){
      productInfo.setProductStatus(0);
      productInfoRepository.save(productInfo);
      return new ResultVO(200,"上架成功");
    }else{
      return new ResultVO(500,"无此商品");
    }

  }

}
