package com.imooc.demo.service.impl;

import com.imooc.demo.converter.OrderMaster2OrderDTOConveter;
import com.imooc.demo.dataobject.OrderDetail;
import com.imooc.demo.dataobject.OrderMaster;
import com.imooc.demo.dataobject.ProductInfo;
import com.imooc.demo.dto.CartDTO;
import com.imooc.demo.dto.OrderDTO;
import com.imooc.demo.enums.OrderStatusEnum;
import com.imooc.demo.enums.PayStatusEnum;
import com.imooc.demo.enums.ResultEnums;
import com.imooc.demo.exception.SellException;
import com.imooc.demo.repository.OrderDetailRepository;
import com.imooc.demo.repository.OrderMasterRepository;
import com.imooc.demo.service.OrderService;
import com.imooc.demo.service.ProductInfoService;
import com.imooc.demo.service.WebSocket;
import com.imooc.demo.utils.KeyUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
@Autowired
  ProductInfoService productInfoService;
@Autowired
  OrderDetailRepository orderDetailRepository;
@Autowired OrderMasterRepository orderMasterRepository;
@Autowired
  WebSocket webSocket;
@Override
@Transactional
  public OrderDTO createOrder(OrderDTO orderDTO) {
    String orderId = KeyUtil.genUniqueKey();
    BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
  //  1.查询商品价格，库存.
    List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
//    List<CartDTO> cartDTOList = new ArrayList<>();

    for (OrderDetail orderDetail: orderDetailList){
      ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
        if (productInfo ==null){
          throw  new SellException(ResultEnums.PRODUCT_NOT_EXIST);
        }
      //  2.计算订单总价.

      orderAmount =  productInfo.getProductPrice().
          multiply(new BigDecimal(orderDetail.getProductQuantity())).
            add(orderAmount);
      //  3.1.将订单写入数据库(OrderDetail).
      BeanUtils.copyProperties(productInfo,orderDetail);

      orderDetail.setOrderId(orderId);
      orderDetail.setDetailId(KeyUtil.genUniqueKey());
      orderDetailRepository.save(orderDetail);
//      CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//      cartDTOList.add(cartDTO);

    }
    //  3.1.将订单写入数据库(OrderDetail).
    OrderMaster orderMaster = new OrderMaster();
  orderDTO.setOrderId(orderId);
  BeanUtils.copyProperties(orderDTO, orderMaster);
    orderMaster.setOrderAmount(orderAmount);
    orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderMaster.setOrderStatus(PayStatusEnum.WAIT.getCode());
    orderMasterRepository.save(orderMaster);
    //  4.支付成功扣库存.
    List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e-> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(
        Collectors.toList());

    productInfoService.decreaseStock(cartDTOList);
    //发送webSocket消息
      webSocket.sendMessage("您有新的胃来外卖订单");

    return orderDTO;

  }

  @Override
  public Page<OrderDTO> findList(Pageable pageable) {
    Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
    List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConveter.conveter(orderMasterPage.getContent());
    return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
  }

  @Override
  public OrderDTO findOne(String orderId) {
 OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
  if (orderMaster ==null){
    throw new  SellException(ResultEnums.ORDER_NOT_EXIST);
  }
  List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
  OrderDTO orderDTO = new OrderDTO();
  BeanUtils.copyProperties(orderMaster,orderDTO);
  orderDTO.setOrderDetailList(orderDetailList);
  return orderDTO;
  }

  @Override
  public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

    Page<OrderMaster> orderMaster = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
    List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConveter.conveter(orderMaster.getContent());
    Page<OrderDTO> orderDTOPage =new PageImpl<OrderDTO>(orderDTOList,pageable,orderMaster.getTotalElements());
    return orderDTOPage;
  }

  @Override
  @Transactional
  public OrderDTO cancel(OrderDTO orderDTO) {
  //判断订单状态是否正确
  if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
    throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
  }
  //修改订单状态
//    OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
    OrderMaster orderMaster = new OrderMaster();
  BeanUtils.copyProperties(orderDTO,orderMaster);
  orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
//  orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
  OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
  if (orderMaster1==null){
    log.error("【取消订单】订单状态不正确,",orderMaster);
    throw new SellException(ResultEnums.ORDER_CANCEL_FAILD);
  }

    //返回库存
    if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
      log.error("【取消订单】,订单中无商品,orderId={}",orderDTO.getOrderId());
      throw new SellException(ResultEnums.ORDER_DETAIL_EMPTY);
    }
    List<CartDTO> cartDTOList = new ArrayList<>();
    for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){
      CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
      cartDTOList.add(cartDTO);
    }
    productInfoService.increaseStock(cartDTOList);
    //如果已支付，退款
    if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)){
      //TODO
    }
    return orderDTO;
  }

  @Override
  public OrderDTO finish(OrderDTO orderDTO) {
  //判断订单状态
  if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
    log.error("【订单完结】订单状态异常,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
    throw  new SellException(ResultEnums.ORDER_UPDATE_FAIL);
  }
  //修改订单状态
    orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO,orderMaster);
    orderMasterRepository.save(orderMaster);
    return orderDTO;
  }

  @Override
  public OrderDTO paid(OrderDTO orderDTO) {
  //判断订单状态
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
      log.error("【订单完结】订单状态异常,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
      throw  new SellException(ResultEnums.ORDER_UPDATE_FAIL);
    }

    //判断支付状态
  if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
    log.error("【支付完结】支付状态异常orderId:{},payStatus:{}",orderDTO.getOrderId(),orderDTO.getPayStatus());
    throw new SellException(ResultEnums.ORDER_PAID_FAIL);

  }
  //修改支付状态
  orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
  OrderMaster orderMaster = new OrderMaster();
  BeanUtils.copyProperties(orderDTO,orderMaster);
  OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
  if (orderMaster1 ==null){
    log.error("【完结订单】,订单支付失败,orderMaster={}",orderMaster);
    throw new SellException(ResultEnums.ORDER_UPDATE_FAIL);
  }
    return orderDTO;
  }
}
