<html>
<#include "../common/head.ftl">
<body>
<br>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-6 col-md-offset-3 column">
      <form class="form-horizontal" role="form">
        <div class="form-group">
          <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
          <div class="col-sm-10">
            <input type="email" class="form-control" id="inputEmail3" />
          </div>
        </div>
        <div class="form-group">
          <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
          <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword3" />
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
              <label><input type="checkbox" />Remember me</label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">登陆</button>
            <button type="button" class="btn btn-default" onclick="window.open('${url}')">github授权登陆</button>

          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>