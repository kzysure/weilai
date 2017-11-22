<html>
<head>
  <meta charset="utf-8">
  <title>卖家商品列表</title>
  <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet"></head>
<body>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-4 column">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>
            订单Id
          </th>
          <th>
            订单总金额
          </th>

        </tr>
        </thead>
        <tbody>
        <tr>
          <td>
            ${orderDTO.orderId}
          </td>
          <td>
          ${orderDTO.orderAmount}
          </td>

        </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div class="row clearfix">
    <div class="col-md-12 column">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>
            商品id
          </th>
          <th>
            商品名称
          </th>
          <th>
            价格
          </th>
          <th>
            数量
          </th>
          <th>          总额
          </th>
        </tr>
        </thead>
        <tbody>
        <#list orderDTO.orderDetailList as od>
        <tr>
          <td>
          ${od.productId}
          </td>
          <td>
          ${od.productName}
          </td>
          <td>
          ${od.productPrice}
          </td>
          <td>
          ${od.productQuantity}
          </td>
          <td>
          ${od.productQuantity*od.productPrice}
          </td>
        </tr>

        </#list>

        </tbody>
      </table>
      <#if orderDTO.orderStatus = 0 && orderDTO.payStatus = 1>
        <button type="button" class="btn btn-success btn-lg" onclick="window.open('/sell/seller/order/finish?orderId=${orderDTO.orderId}')">完结订单</button>
      </#if>
    <#if orderDTO.payStatus = 0 && orderDTO.orderStatus = 0>
      <button type="button" class="btn btn-danger btn-lg" onclick="window.open('/sell/seller/order/list')">取消订单</button>
    </#if>

    </div>
  </div>

</div>
</body>
</html>
