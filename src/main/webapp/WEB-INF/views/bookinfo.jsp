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

        <div class="page-header">
            <h1>${book_name}<small>${book_author}</small></h1>
        </div>

        <div class="jumbotron">
            <div class="container">

                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                    <div class="well col-xs-12 col-sm-12 col-md-12 col-lg-12"
                         style="width: 180px; height: 250px;">

                    </div>
                </div>

                <div class=" col-sm-8 col-md-8 col-lg-8">
                    <div>
                        <p>${book_note}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">余量</h3>
                </div>
                <div class="panel-body">
                    ${book_count}
                    <span class="label label-success">余量充足</span>
                </div>

            </div>


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">谁在看</h3>
                </div>
                <div class="panel-body">
                    user1
                </div>
            </div>

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">基本信息</h3>
                </div>
                <div class="panel-body">

                    <table class="table table-bordered table-hover">
                        <tbody>
                        <tr>
                            <td>出版社：</td><td>${book_pub}</td>
                        </tr>
                        <tr>
                            <td>作者：</td><td>${book_author}</td>
                        </tr>
                        <tr>
                            <td>入馆时间：</td><td>${book_intime}</td>
                        </tr>
                        <tr>
                            <td>书籍类型：</td><td>
                            <span class="label label-info">${book_type}</span>
                        </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>

</div>
<%@include file="component_script.jsp"%>
<script src="../statics/js/bookModule.js"></script>
</body>
</html>
