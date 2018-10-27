<%--
  Created by IntelliJ IDEA.
  User: tuxzx
  Date: 2018/9/29
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>图书信息管理系统</title>
    <link rel="stylesheet" href="../statics/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <%@include file="component_nav.jsp"%>
    </div>
    <div class="row clearfix">
        <div class="col-md-12">
            <div id="content">
                ${content}
            </div>
        </div>
    </div>
</div>



<%@include file="component_script.jsp"%>
</body>
</html>