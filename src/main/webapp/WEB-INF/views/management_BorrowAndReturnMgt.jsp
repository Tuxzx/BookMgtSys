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
    <title>借阅管理————图书信息管理系统</title>
    <link rel="stylesheet" href="../statics/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <%@include file="component_nav_management.jsp"%>

        <div class="row clearfix">
            <div class="col-md-6 column">
                <h1>借阅</h1>
                <form id="borrowForm" role="form" onsubmit="return toBorrow()">
                    <div class="form-group">
                        <label for="borrow_stu_id">借阅人ID</label><input type="text" class="form-control" id="borrow_stu_id" name="stu_id" />
                    </div>
                    <div class="form-group">
                        <label for="borrow_book_isbn">书籍ISBN</label><input type="text" class="form-control" id="borrow_book_isbn" name="book_isbn" />
                    </div>
                    <button type="submit" class="btn btn-primary">借阅提交</button>
                </form>
            </div>
            <div class="col-md-6 column">
                <h1>归还</h1>
                <form id="returnForm" role="form" onsubmit="return toReturn()">
                    <div class="form-group">
                        <label for="return_borrow_id">借阅号</label><input type="number" class="form-control" id="return_borrow_id" name="borrow_id" />
                    </div>
                    <div class="form-group">
                        <label for="return_stu_id">借阅人ID</label><input type="text" class="form-control" id="return_stu_id" name="stu_id" />
                    </div>
                    <div class="form-group">
                        <label for="return_book_isbn">书籍ISBN</label><input type="text" class="form-control" id="return_book_isbn" name="book_isbn" />
                    </div>
                    <button type="submit" class="btn btn-primary">还书提交</button>
                </form>

            </div>
        </div>

    </div>
    <%@include file="component_script.jsp"%>
    <script src="../statics/js/manager_br_mgt.js"></script>
</body>
</html>
