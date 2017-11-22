<html>
<head>
  <meta charset="utf-8">
  <title>卖家商品列表</title>
  <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="../../static/css/style.css">
  <style>
    body {
      position: relative;
      overflow-x: hidden;
    }
    body,
    html {
      height: 100%;
      /*background-color: #583e7e;*/
    }
    .nav .open > a {
      background-color: transparent;
    }
    .nav .open > a:hover {
      background-color: transparent;
    }
    .nav .open > a:focus {
      background-color: transparent;
    }
    /*-------------------------------*/
    /*           Wrappers            */
    /*-------------------------------*/
    #wrapper {
      -moz-transition: all 0.5s ease;
      -o-transition: all 0.5s ease;
      -webkit-transition: all 0.5s ease;
      padding-left: 0;
      transition: all 0.5s ease;
    }
    #wrapper.toggled {
      padding-left: 180px;
    }
    #wrapper.toggled #sidebar-wrapper {
      width: 180px;
    }
    #sidebar-wrapper {
      -moz-transition: all 0.5s ease;
      -o-transition: all 0.5s ease;
      -webkit-transition: all 0.5s ease;
      background: #1a1a1a;
      height: 100%;
      left: 220px;
      margin-left: -220px;
      overflow-x: hidden;
      overflow-y: auto;
      transition: all 0.5s ease;
      width: 0;
      z-index: 1000;
    }
    #sidebar-wrapper::-webkit-scrollbar {
      display: none;
    }
    #page-content-wrapper {
      padding-top: 70px;
      width: 100%;
    }
    /*-------------------------------*/
    /*     Sidebar nav styles        */
    /*-------------------------------*/
    .sidebar-nav {
      list-style: none;
      margin: 0;
      padding: 0;
      position: absolute;
      top: 0;
      width: 220px;
    }
    .sidebar-nav li {
      display: inline-block;
      line-height: 20px;
      position: relative;
      width: 100%;
    }
    .sidebar-nav li:before {
      background-color: #1c1c1c;
      content: '';
      height: 100%;
      left: 0;
      position: absolute;
      top: 0;
      -webkit-transition: width 0.2s ease-in;
      transition: width 0.2s ease-in;
      width: 3px;
      z-index: -1;
    }
    .sidebar-nav li:first-child a {
      background-color: #1a1a1a;
      color: #ffffff;
    }
    .sidebar-nav li:nth-child(2):before {
      background-color: #402d5c;
    }
    .sidebar-nav li:nth-child(3):before {
      background-color: #4c366d;
    }
    .sidebar-nav li:nth-child(4):before {
      background-color: #583e7e;
    }
    .sidebar-nav li:nth-child(5):before {
      background-color: #64468f;
    }
    .sidebar-nav li:nth-child(6):before {
      background-color: #704fa0;
    }
    .sidebar-nav li:nth-child(7):before {
      background-color: #7c5aae;
    }
    .sidebar-nav li:nth-child(8):before {
      background-color: #8a6cb6;
    }
    .sidebar-nav li:nth-child(9):before {
      background-color: #987dbf;
    }
    .sidebar-nav li:hover:before {
      -webkit-transition: width 0.2s ease-in;
      transition: width 0.2s ease-in;
      width: 100%;
    }
    .sidebar-nav li a {
      color: #dddddd;
      display: block;
      padding: 10px 15px 10px 30px;
      text-decoration: none;
    }
    .sidebar-nav li.open:hover before {
      -webkit-transition: width 0.2s ease-in;
      transition: width 0.2s ease-in;
      width: 100%;
    }
    .sidebar-nav .dropdown-menu {
      background-color: #222222;
      border-radius: 0;
      border: none;
      box-shadow: none;
      margin: 0;
      padding: 0;
      position: relative;
      width: 100%;
    }
    .sidebar-nav li a:hover,
    .sidebar-nav li a:active,
    .sidebar-nav li a:focus,
    .sidebar-nav li.open a:hover,
    .sidebar-nav li.open a:active,
    .sidebar-nav li.open a:focus {
      background-color: transparent;
      color: #ffffff;
      text-decoration: none;
    }
    .sidebar-nav > .sidebar-brand {
      font-size: 20px;
      height: 65px;
      line-height: 44px;
    }
    /*-------------------------------*/
    /*       Hamburger-Cross         */
    /*-------------------------------*/
    .hamburger {
      background: transparent;
      border: none;
      display: block;
      height: 32px;
      margin-left: 15px;
      position: fixed;
      top: 20px;
      width: 32px;
      z-index: 999;
    }
    .hamburger:hover {
      outline: none;
    }
    .hamburger:focus {
      outline: none;
    }
    .hamburger:active {
      outline: none;
    }
    .hamburger.is-closed:before {
      -webkit-transform: translate3d(0, 0, 0);
      -webkit-transition: all 0.35s ease-in-out;
      color: #ffffff;
      content: '';
      display: block;
      font-size: 14px;
      line-height: 32px;
      opacity: 0;
      text-align: center;
      width: 100px;
    }
    .hamburger.is-closed:hover before {
      -webkit-transform: translate3d(-100px, 0, 0);
      -webkit-transition: all 0.35s ease-in-out;
      display: block;
      opacity: 1;
    }
    .hamburger.is-closed:hover .hamb-top {
      -webkit-transition: all 0.35s ease-in-out;
      top: 0;
    }
    .hamburger.is-closed:hover .hamb-bottom {
      -webkit-transition: all 0.35s ease-in-out;
      bottom: 0;
    }
    .hamburger.is-closed .hamb-top {
      -webkit-transition: all 0.35s ease-in-out;
      background-color: rgba(255, 255, 255, 0.7);
      top: 5px;
    }
    .hamburger.is-closed .hamb-middle {
      background-color: rgba(255, 255, 255, 0.7);
      margin-top: -2px;
      top: 50%;
    }
    .hamburger.is-closed .hamb-bottom {
      -webkit-transition: all 0.35s ease-in-out;
      background-color: rgba(255, 255, 255, 0.7);
      bottom: 5px;
    }
    .hamburger.is-closed .hamb-top,
    .hamburger.is-closed .hamb-middle,
    .hamburger.is-closed .hamb-bottom,
    .hamburger.is-open .hamb-top,
    .hamburger.is-open .hamb-middle,
    .hamburger.is-open .hamb-bottom {
      height: 4px;
      left: 0;
      position: absolute;
      width: 100%;
    }
    .hamburger.is-open .hamb-top {
      -webkit-transform: rotate(45deg);
      -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
      background-color: #ffffff;
      margin-top: -2px;
      top: 50%;
    }
    .hamburger.is-open .hamb-middle {
      background-color: #ffffff;
      display: none;
    }
    .hamburger.is-open .hamb-bottom {
      -webkit-transform: rotate(-45deg);
      -webkit-transition: -webkit-transform 0.2s cubic-bezier(0.73, 1, 0.28, 0.08);
      background-color: #ffffff;
      margin-top: -2px;
      top: 50%;
    }
    .hamburger.is-open:before {
      -webkit-transform: translate3d(0, 0, 0);
      -webkit-transition: all 0.35s ease-in-out;
      color: #ffffff;
      content: '';
      display: block;
      font-size: 14px;
      line-height: 32px;
      opacity: 0;
      text-align: center;
      width: 100px;
    }
    .hamburger.is-open:hover before {
      -webkit-transform: translate3d(-100px, 0, 0);
      -webkit-transition: all 0.35s ease-in-out;
      display: block;
      opacity: 1;
    }

  </style>
</head>
<body>
<div class="toggled" id="wrapper">
  <#--SideBar-->
  <#include "./nav.ftl">
  <#--Content-->
    <div id="page-content-wrapper">
      <div class="container-fluid">
        <div class="row clearfix">
          <div class="col-md-12 column">
            <table class="table table-bordered table-condensed">
              <thead>
              <tr>
                <th>订单ID</th>
                <th>姓名</th>
                <th>手机号</th>
                <th>地址</th>
                <th>金额</th>
                <th>订单状态</th>
                <th>支付状态</th>
                <th>创建时间</th>
                <th colspan="2">操作</th>
              </tr>
              </thead>
              <tbody>
              <#list orderDTOPage.content as orderDTO>
              <tr>
                <td>
                ${orderDTO.orderId}
                </td>
                <td>
                ${orderDTO.buyerName}
                </td>
                <td>
                ${orderDTO.buyerPhone}
                </td>
                <td>
                ${orderDTO.buyerAddress}
                </td>
                <td>
                ${orderDTO.orderAmount}
                </td>
                <td>
                  <#if orderDTO.orderStatus = 0>
                    新订单
                  <#elseif orderDTO.orderStatus = 1>
                    已完成订单
                  <#elseif orderDTO.orderStatus = 2>
                    已取消订单
                  <#else>
                    未知订单
                  </#if>
                <#--${orderDTO.orderStatus}-->
                </td>
                <td>
                  <#if orderDTO.payStatus = 0>
                    未支付
                  <#elseif orderDTO.payStatus = 1>
                    已支付
                  <#else>
                    未知支付状态
                  </#if>
                </td>
                <td>
                ${orderDTO.createTime}
                </td>
                <td>
                  <a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                </td>
                <td>
                  <#if orderDTO.orderStatus = 0 &&orderDTO.payStatus != 1 >
                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>

                  </#if>
                </td>
              </tr>
              </#list>
              </tbody>
            </table>
          </div>
          <div class="col-md-12 column">
            <ul class="pagination pull-right">
            <#if currentPage == 1>
              <li class="disabled">
                <a href="/sell/seller/order/list?page=${prePage}&size=10">上一页</a>
              </li>
            <#else>
              <li>
                <a href="/sell/seller/order/list?page=${prePage}&size=10">上一页</a>
              </li>
            </#if>

            <#list 1.. orderDTOPage.getTotalPages() as index>
              <#if currentPage == index>
                <li class="disabled">
                  <a href="/sell/seller/order/list?page=${index}&size=10" >${index}</a>
                </li>
              <#else>
                <li>
                  <a href="/sell/seller/order/list?page=${index}&size=10" >${index}</a>
                </li>
              </#if>

            </#list>
            <#if currentPage=orderDTOPage.getTotalPages()>
            <li class="disabled">
              <a href="#">下一页</a>
            <#else>
              <li>
                <a href="/sell/seller/order/list?page=${nextPage}&size=10">下一页</a>
              </li>
              </li>
            </#if>

            </ul>
          </div>
        </div>
      </div>
      <div style="z-index: 9999;position: fixed;right: 3px;bottom: 3px;width:">
        <button onclick="fullScreen()>全屏</button>

      </div>

    </div>

</div>
<div style="">
  <a onclick="fullScreen()" style="cursor: pointer">📱全屏</a>
</div>
<script>
  var isFullScreen = false;
  var de =document.documentElement;
  var dex = document;
  function fullScreen() {
    isFullScreen = !isFullScreen;
    console.log(isFullScreen);
    if(isFullScreen === true){
      if (de.mozRequestFullScreen) {
      de.mozRequestFullScreen();
    } else if (de.webkitRequestFullScreen) {
      de.webkitRequestFullScreen();
    }

    }
    else if(isFullScreen === false){
      if (dex.mozCancelFullScreen) {
      dex.mozCancelFullScreen();
    } else if (dex.webkitCancelFullScreen) {
      dex.webkitCancelFullScreen();
    }
    }
  }

//  //进入全屏
//  function requestFullScreen() {
//    var de = document.documentElement;
//    if (de.requestFullscreen) {
//      de.requestFullscreen();
//    } else if (de.mozRequestFullScreen) {
//      de.mozRequestFullScreen();
//    } else if (de.webkitRequestFullScreen) {
//      de.webkitRequestFullScreen();
//    }
//  }
//  //退出全屏
//  function exitFullscreen() {
//    var de = document;
//    if (de.exitFullscreen) {
//      de.exitFullscreen();
//    } else if (de.mozCancelFullScreen) {
//      de.mozCancelFullScreen();
//    } else if (de.webkitCancelFullScreen) {
//      de.webkitCancelFullScreen();
//    }
//  }
</script>
</body>
</html>
