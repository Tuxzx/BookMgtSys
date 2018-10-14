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
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <nav class="navbar navbar-default navbar-static-top-top">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">图书管理系统</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">主页<span class="sr-only">(current)</span></a></li>
                        <li><a href="#">借阅状态</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">更多<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">借阅历史记录</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">本周借阅排行</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">入馆新书</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Message <span class="badge">100</span></a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${username}</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 安全退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
    <div class="row clearfix">

        <div class="page-header">
            <h1>${book_name}<small>${book_author}</small></h1>
        </div>

        <div class="jumbotron">
            <div class="container">

                <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
                    <div class="well col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <br><br><br><br><br><br><br><br>
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
                            <span class="label label-info">类型</span>
                        </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>

</div>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/bookModule.js"></script>
</body>
</html>
