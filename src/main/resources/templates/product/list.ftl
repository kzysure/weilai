<html>
<#include "../common/head.ftl">
<body>
<div class="container-fluid">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <table class="table table-bordered table-condensed">
        <thead>
        <tr>
          <th>商品ID</th>
          <th>名称</th>
          <th>图片</th>
          <th>单价</th>
          <th>库存</th>
          <th>描述</th>
          <th>类目</th>
          <th>创建时间</th>
          <th>修改时间</th>
          <th colspan="2">操作</th>
        </tr>
        </thead>
        <tbody>
        <#list productPage.getContent() as p>
        <tr>
          <td>
          ${p.productId}
          </td>
          <td>
          ${p.productName}

          </td>
          <td>
          <#--${p.productIcon}-->
            <img src="${p.productIcon}" alt="商品图片" width="50px" height="50px">

          </td>
          <td>
          ${p.productPrice}

          </td>
          <td>
          ${p.productStock}

          </td>
          <td>
          ${p.productDescription}

          </td>
          <td>
        ${p.categoryType}

          </td>
          <td>
          ${p.createTime}
          </td>
          <td>
          ${p.updateTime}
          </td>
          <td>
            <a href="/sell/seller/product/update?productId=${p.productId}">修改</a>
          </td>
          <td>
            <#if  1= 1 >
              <a href="/sell/seller/product/notsell?productId=${p.productId}">下架</a>

            </#if>
          </td>
        </tr>
        </#list>
        </tbody>
      </table>
    </div>
    <#--<div class="col-md-12 column">-->
      <#--<ul class="pagination pull-right">-->
      <#--<#if currentPage == 1>-->
        <#--<li class="disabled">-->
          <#--<a href="/sell/seller/order/list?page=${prePage}&size=10">上一页</a>-->
        <#--</li>-->
      <#--<#else>-->
        <#--<li>-->
          <#--<a href="/sell/seller/order/list?page=${prePage}&size=10">上一页</a>-->
        <#--</li>-->
      <#--</#if>-->

      <#--<#list 1.. orderDTOPage.getTotalPages() as index>-->
        <#--<#if currentPage == index>-->
          <#--<li class="disabled">-->
            <#--<a href="/sell/seller/order/list?page=${index}&size=10" >${index}</a>-->
          <#--</li>-->
        <#--<#else>-->
          <#--<li>-->
            <#--<a href="/sell/seller/order/list?page=${index}&size=10" >${index}</a>-->
          <#--</li>-->
        <#--</#if>-->

      <#--</#list>-->
      <#--<#if currentPage=orderDTOPage.getTotalPages()>-->
      <#--<li class="disabled">-->
        <#--<a href="#">下一页</a>-->
      <#--<#else>-->
        <#--<li>-->
          <#--<a href="/sell/seller/order/list?page=${nextPage}&size=10">下一页</a>-->
        <#--</li>-->
        <#--</li>-->
      <#--</#if>-->

      <#--</ul>-->
    <#--</div>-->
  </div>
</div>

</body>
</html>