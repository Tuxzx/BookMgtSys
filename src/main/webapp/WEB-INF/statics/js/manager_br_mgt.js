function toBorrow() {
    $.ajax({
        type:"post",
        url:"/user/postBorrow",
        data:$("#borrowForm").serialize(),
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

function toReturn() {
    $.ajax({
        type:"post",
        url:"/user/postReturn",
        data:$("#returnForm").serialize(),
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