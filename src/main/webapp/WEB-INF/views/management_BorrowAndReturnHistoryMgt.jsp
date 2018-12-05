<%--
  Created by IntelliJ IDEA.
  User: tuxzx
  Date: 2018/10/27
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>借阅记录————图书信息管理系统</title>
    <link rel="stylesheet" href="../statics/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <%@include file="component_nav_management.jsp"%>

        <h1>借阅记录</h1>
        ${content}
    </div>
    <%@include file="component_script.jsp"%>
    <script src="../statics/js/manager_br_mgt.js"></script>
</body>
</html>
