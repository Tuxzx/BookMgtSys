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
    <title>图书管理————图书信息管理系统</title>
    <link rel="stylesheet" href="../statics/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <%@include file="component_nav_management.jsp"%>

        <div row clearfix>
            <div class="col-md-8 col-lg-8">
                <input class="form-control" id="searchText" type="text" placeholder="请输入书籍ISBN或书名查询">
            </div>
            <div class="col-md-2 col-lg-2">
                <button class="btn btn-default btn-block" onclick="getBookByISBN()">按ISBN</button>
            </div>
            <div class="col-md-2 col-lg-2">
                <button class="btn btn-default btn-block" onclick="getBooksByName()">按书名</button>
            </div>
        </div>
        <div row clearfix>
            <div class="col-md-12 col-lg-12">
                <br>
                <button class="btn btn-info btn-block" onclick="getAddBookForm()">添加新书</button>
            </div>
        </div>
        <div row clearfix>
            <div class="col-md-12 col-lg-12">
                <br>
                <div id="content"></div>
            </div>
        </div>






    </div>
    <script src="../statics/js/manager_bookmgt.js"></script>
    <%@include file="component_script.jsp"%>
</body>
</html>
