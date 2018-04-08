package com.kzysure.demo.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kzysure.demo.VO.OrderConfirmVO;
import com.kzysure.demo.VO.OrderVO;
import com.kzysure.demo.VO.ResultVO;
import com.kzysure.demo.converter.OrderForm2OrderDTOConverter;
import com.kzysure.demo.dataobject.OrderDetail;
import com.kzysure.demo.dataobject.OrderMaster;
import com.kzysure.demo.dataobject.ProductInfo;
import com.kzysure.demo.dto.OrderDTO;
import com.kzysure.demo.enums.OrderStatusEnum;
import com.kzysure.demo.enums.PayStatusEnum;
import com.kzysure.demo.enums.ResultEnums;
import com.kzysure.demo.exception.SellException;
import com.kzysure.demo.form.ListForm;
import com.kzysure.demo.form.OrderForm;
import com.kzysure.demo.repository.OrderDetailRepository;
import com.kzysure.demo.repository.OrderMasterRepository;
import com.kzysure.demo.repository.ProductInfoRepository;
import com.kzysure.demo.service.BuyerService;
import com.kzysure.demo.service.OrderService;
import com.kzysure.demo.utils.ResultVoUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
  @Autowired
  OrderService orderService;
  @Autowired
  BuyerService buyerService;
  @Autowired
  ProductInfoRepository productInfoRepository;
  @Autowired
  OrderMasterRepository orderMasterRepository;
  @Autowired
  OrderDetailRepository orderDetailRepository;
  //创建商品
  @PostMapping("/create")
  public ResultVO<Map<String,String>> createOrder(@Valid OrderForm orderForm,BindingResult bindingResult){
    System.out.println(orderForm.toString());
    if (bindingResult.hasErrors()){
      log.error("【创建订单】参数不正确，orderForm：{}",orderForm);
      throw new SellException(ResultEnums.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
    }
    OrderForm2OrderDTOConverter orderForm2OrderDTOConverter = new OrderForm2OrderDTOConverter();
    OrderDTO orderDTO = orderForm2OrderDTOConverter.orderForm2orderDTOConverter(orderForm);
    if (orderDTO.getOrderDetailList()==null){
      log.error("购物车不能为空");
      throw new SellException(ResultEnums.PARAM_ERROR);
    }
    OrderDTO orderDTO1 = orderService.createOrder(orderDTO);
    if (orderDTO1==null){
      log.error("创建订单失败");
      throw new SellException(ResultEnums.CREATE_ORDER_ERROR);
    }
Map<String,String> map = new HashMap<>();
    map.put("orderId",orderDTO1.getOrderId());
    return ResultVoUtil.success(map);
  }
  @GetMapping("/list")
  public ResultVO<List<OrderDTO>> findList(ListForm listForm){
    if (listForm.getOpenid()==null){
      log.error("【查询订单】,Openid为空");
      throw new SellException(ResultEnums.PARAM_ERROR);
    }
    PageRequest pageRequest = new PageRequest(listForm.getPage(),listForm.getSize());
    Page<OrderDTO> orderDTOPage = orderService.findList(listForm.getOpenid(),pageRequest);
return ResultVoUtil.success(orderDTOPage.getContent());
  }
  @GetMapping("/detail")
  public ResultVO<OrderDTO> finOne(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
      OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
      return ResultVoUtil.success(orderDTO);
  }
  @PostMapping("/cancel")
  public ResultVO cancelOrder(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
    OrderDTO orderDTO = buyerService.cancelOrder(openid,orderId);
    return ResultVoUtil.success();
  }
  @PostMapping("/orderConfirm")
  public List<OrderConfirmVO> orderConfirm(@RequestParam("items") String items){
    Gson gson = new Gson();
    List<OrderDetail> orderDetailList = new ArrayList<>();
    try{
      orderDetailList = gson.fromJson(items,new TypeToken<List<OrderDetail>>(){}.getType());

    }catch(Exception e){
      log.error("【对象转换异常】,string={}",items);
      throw new SellException(ResultEnums.PARAM_ERROR);
    }
    List<String> itemsList = new ArrayList<>();
    List<OrderConfirmVO> orderConfirmVOS = new ArrayList<>();
    for (OrderDetail orderDetail:orderDetailList){
      itemsList.add(orderDetail.getProductId());
    }
    List<ProductInfo> productInfos = productInfoRepository.findAll(itemsList);
    for (ProductInfo productInfo:productInfos){
      for (OrderDetail orderDetail:orderDetailList){
        if (productInfo.getProductId().equals(orderDetail.getProductId())){
          //组装orderconfirm
          OrderConfirmVO orderConfirmVO = new OrderConfirmVO();
          BigDecimal bd6 = new BigDecimal(orderDetail.getProductQuantity());
          orderConfirmVO.setOrderAmount(productInfo.getProductPrice().multiply(bd6));
          orderConfirmVO.setProductName(productInfo.getProductName());
          orderConfirmVO.setProductQuantity(orderDetail.getProductQuantity());
          orderConfirmVO.setProductIcon(productInfo.getProductIcon());
          orderConfirmVOS.add(orderConfirmVO);
        }
      }
    }
    return orderConfirmVOS;
  }
  @PostMapping("/orderPay")
  public ResultVO orderPay(@RequestParam("orderId") String orderId){
   OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
    orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());

    OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
    if (null != orderMaster1){
      ResultVO resultVO = new ResultVO();
       resultVO.setMsg("支付成功");
       resultVO.setCode(200);
       return resultVO;
    }else{
      ResultVO resultVO = new ResultVO(200,"支付失败");

      return resultVO;
    }
  }
  @GetMapping("/orderList")
  public List<OrderVO> getOrderList(@RequestParam("openid") String openid){
    List<OrderMaster> orderMasters = orderMasterRepository.findOrderMastersByBuyerOpenidOrderByCreateTimeDesc(openid);
    List<OrderVO> orderVOList = new ArrayList<>();
    for (OrderMaster orderMaster:orderMasters){
      OrderVO orderVO = new OrderVO();
      BeanUtils.copyProperties(orderMaster,orderVO);
      List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
      orderVO.setOrderDetailList(orderDetailList);
      orderVOList.add(orderVO);

    }
    return orderVOList;
  }
  @PostMapping("/finishOrder")
  public ResultVO finishOrder(@RequestParam("orderId") String orderId){
    OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
    orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    orderMasterRepository.save(orderMaster);
    return new ResultVO(200,"确认收货成功");
  }
}
