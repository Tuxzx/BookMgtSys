<%--
  Created by IntelliJ IDEA.
  User: tuxzx
  Date: 2018/6/28
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- Bootshrap --%>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <title>Error</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="jumbotron">
                <h1>
                    Something wrong!
                </h1>
                <p>
                    ${message}
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="/">Home</a>
                </p>
            </div>
        </div>
    </div>
</div>

<%-- 插入jquery和 bootstrap --%>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
