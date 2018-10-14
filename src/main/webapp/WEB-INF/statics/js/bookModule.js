
function getBookAllType(){
    $.ajax({
        type:"get",
        url:"/getBookAllType",
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == 200) {
                console.log("200 execute");
                $("#category")[0].innerHTML = data.info;
            } else {
                console.log("404 execute");
                alert(data.info);
            }
        },
        error:function (data) {
            console.log(data);
            alert("error");
            alert(data);
        }

    });
}

function getBookByType(typename){
    console.log(typename);
    $.ajax({
        type:"get",
        url:"/getBookByType",
        data:{"type":typename},
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == 200) {
                console.log("200 execute");
                $("#bookinfo")[0].innerHTML = data.info;
            } else {
                console.log("404 execute");
                alert(data.info);
            }
        },
        error:function (data) {
            console.log(data);
            alert("error");
            alert(data);
        }

    });
}

$(document).ready(getBookAllType());
$(document).ready(getBookByType(0));