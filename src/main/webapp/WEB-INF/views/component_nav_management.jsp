<%--
  Created by IntelliJ IDEA.
  User: tuxzx
  Date: 2018/10/27
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">图书信息管理系统</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/login">主页</a></li>
                <li><a href="/toBookMgt">图书信息管理</a></li>
                <li><a href="/toStudentMgt">借阅人管理</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        借阅管理 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/toBorrowAndReturn">借阅管理</a></li>
                        <li class="divider"></li>
                        <li><a href="/user/toBorrowAndReturnHistory">借阅记录</a></li>
                    </ul>
                </li>
            </ul>

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
        </div>
    </div>
</nav>
