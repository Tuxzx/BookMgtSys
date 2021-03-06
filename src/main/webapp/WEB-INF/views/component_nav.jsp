<%--
  Created by IntelliJ IDEA.
  User: tuxzx
  Date: 2018/10/27
  Time: 01:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top-top" onclick="activeClick(event)">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/login">图书管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class=""><a href="/login">主页<span class="sr-only">(current)</span></a></li>
                <li><a href="/user/toBorrowedBook">借阅状态</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">更多<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/toBorrowedBookHistory">借阅历史记录</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/user/toBorrowedBookRanking">本周借阅排行</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/toNewerBookList">入馆新书</a></li>
                    </ul>
                </li>
            </ul>
            <form id="nav_search" class="navbar-form navbar-left" method="post" action="/getBookByNameNav">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="请输入书名查询" required>
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${nickname} <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${nickname}</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/exitSystem"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 安全退出</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>