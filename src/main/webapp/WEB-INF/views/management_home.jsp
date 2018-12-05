<%--
  Created by IntelliJ IDEA.
  User: tuxzx
  Date: 2018/10/27
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后台管理————图书信息管理系统</title>
    <link rel="stylesheet" href="../statics/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <%@include file="component_nav_management.jsp"%>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h1>
                欢迎你 <small> ${nickname} </small>
            </h1>
            <hr>
            <div class="jumbotron">
                <h2>
                    通知
                </h2>
                <p id="notification">
                </p>
                <p>
                    <a class="btn btn-primary btn-large" data-toggle="modal" data-target="#myModal">新通知</a>
                </p>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加新通知
                </h4>
            </div>
            <div class="modal-body">
                <form id="notifyForm" method="post" role="form" onsubmit="return updateNotification()">
                    <div class="form-group">
                        <label for="notify">通知内容</label>
                        <textarea rows="6" class="form-control" id="notify" name="notification" placeholder="请输入通知内容" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%@include file="component_script.jsp"%>
<script src="../statics/js/manager_home.js"></script>
</body>
</html>
