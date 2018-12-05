function getNewerNotification() {
    $.ajax({
        type:"post",
        url:"/getNewerNotification",
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                $("#notification")[0].innerHTML = data.info;
                console.log(data.status);
            } else if (data.status == "failed") {
                console.log(data.status);
                $("#notification")[0].innerHTML = "请求失败！";
            }
        },
        error:function (data) {
            console.log(data.status);
            console.log(data.info);
            $("#notification")[0].innerHTML = "请求失败！";
        }
    });
}

//自启动
$(document).ready(getNewerNotification());

function updateNotification() {
    $.ajax({
        type:"post",
        url:"/updateNotification",
        data:$("#notifyForm").serialize(),
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == "success") {
                $("#notification")[0].innerHTML = data.info;
                getNewerNotification();
                $('#myModal').modal('hide');
                console.log(data.status);
            } else if (data.status == "failed") {
                console.log(data.status);
                $("#notification")[0].innerHTML = "请求失败！";
                $('#myModal').modal('hide');
            }
        },
        error:function (data) {
            console.log(data.status);
            console.log(data.info);
            $("#notification")[0].innerHTML = "请求失败！";
            $('#myModal').modal('hide');
        }
    });
    return false;
}