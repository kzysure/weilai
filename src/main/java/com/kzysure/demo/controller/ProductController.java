package com.kzysure.demo.controller;

import com.kzysure.demo.dataobject.ProductInfo;
import com.kzysure.demo.service.ProductInfoService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class ProductController {
  @Autowired
  ProductInfoService productInfoService;
  @GetMapping("/list")
  public ModelAndView list( @RequestParam(value = "page",defaultValue = "0") Integer page,@RequestParam(value = "size",defaultValue = "5") Integer size,Map<String,Object> map){
    PageRequest pageRequest = new PageRequest(page,size);
    Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);

    map.put("productPage",productInfoPage);
    return new ModelAndView("product/list",map);

  }

}
