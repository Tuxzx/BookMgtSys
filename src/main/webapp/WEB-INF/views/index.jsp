<%--
  Created by IntelliJ IDEA.
  User: tuxzx
  Date: 2018/9/27
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>图书信息管理系统</title>
  <link rel="stylesheet" href="../statics/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <div class="row" clearfix>
    <div class="page-header">
      <h1>图书信息管理系统<small></small></h1>
    </div>
  </div>

  <div class="row clearfix">
    <div class="col-md-8 col-lg-8">
      <div class="jumbotron">
        <div class="container">
          <h1>登陆</h1>
          <p>看见更大的世界</p>
          <p>
            <a class="btn btn-primary btn-lg">Learn more</a>
          </p>
        </div>
      </div>

    </div>

    <div class="col-md-4 col-lg-4">
      <form id="loginAjax" role="form" method="post" action="/login" onsubmit="return login()" >
        <legend>登陆</legend>
        <div id="tip">
        </div>
        <div class="form-group">
          <label for="username">用户名</label>
          <input type="text" name="username" id="username" class="form-control" placeholder="请输入用户名" required>
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" required>
        </div>
        <div class="form-group btn-group" data-toggle="buttons">
          <label class="btn btn-default active">
            <input type="radio" name="role" id="option1" value="student" checked>学生
          </label>
          <label class="btn btn-default">
            <input type="radio" name="role" id="option2" value="manager">管理员
          </label>
        </div><br>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>

    </div>
  </div>
</div>



<%@include file="component_script.jsp"%>
<script src="../statics/js/loginModule.js"></script>
</body>
</html>
