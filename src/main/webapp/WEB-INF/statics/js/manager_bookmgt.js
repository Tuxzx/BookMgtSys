function getBookByISBN() {
    var searchText = $("#searchText")[0].value;
    $.ajax({
        type:"post",
        url:"/getBookByISBN",
        data: {isbn: searchText},
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                $("#content")[0].innerHTML = data.info;

            } else if (data.status == "failed") {
                $("#content")[0].innerHTML = data.info;
            }
        },
        error:function (data) {
            $("#content")[0].innerHTML = "请求失败!";
        }
    });
}

function getBooksByName() {
    var searchText = $("#searchText")[0].value;
    $.ajax({
        type:"post",
        url:"/getBookByName",
        data: {name: searchText},
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                $("#content")[0].innerHTML = data.info;

            } else if (data.status == "failed") {
                $("#content")[0].innerHTML = data.info;
            }
        },
        error:function (data) {
            $("#content")[0].innerHTML = "请求失败!";
            console.log(data);
        }
    });
}
// 按钮change
function getUpdateBookForm(isbn) {
    $.ajax({
        type:"post",
        url:"/getBookUpdateForm",
        data: {isbn: isbn},
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                $("#content")[0].innerHTML = data.info;

            } else if (data.status == "failed") {
                $("#content")[0].innerHTML = data.info;
            }
        },
        error:function (data) {
            $("#content")[0].innerHTML = "请求失败!";
            console.log(data);
        }
    });
}

function getAddBookForm() {
    $.ajax({
        type:"get",
        url:"/getBookAddForm",
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                $("#content")[0].innerHTML = data.info;

            } else if (data.status == "failed") {
                $("#content")[0].innerHTML = data.info;
            }
        },
        error:function (data) {
            $("#content")[0].innerHTML = "请求失败!";
            console.log(data);
        }
    });
}

function updateBook() {
    $.ajax({
        type:"post",
        url:"/updateBook",
        data:$("#bookForm").serialize(),
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                alert(data.info);
                console.log(data.status);
            } else if (data.status == "failed") {
                alert(data.info);
                console.log(data.status);
            }
        },
        error:function (data) {
            console.log(data.status);
            console.log(data.info);
            alert("请求出现问题！");

        }
    });
    return false;
}

function addNewBook() {
    $.ajax({
        type:"post",
        url:"/addNewBook",
        data:$("#bookForm").serialize(),
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                alert(data.info);
                console.log(data.status);
            } else if (data.status == "failed") {
                alert(data.info);
                console.log(data.status);
            }
        },
        error:function (data) {
            console.log(data.status);
            console.log(data.info);
            alert("请求出现问题！");

        }
    });
    return false;
}

function deleteBook(isbn) {
    $.ajax({
        type:"post",
        url:"/deleteBook",
        data:{book_isbn: isbn},
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                alert(data.info);
                $("#content")[0].innerHTML = "";
                console.log(data.status);
            } else if (data.status == "failed") {
                alert(data.info);
                console.log(data.status);
            }
        },
        error:function (data) {
            console.log(data.status);
            console.log(data.info);
            alert("请求出现问题！");

        }
    });
    return false;
}