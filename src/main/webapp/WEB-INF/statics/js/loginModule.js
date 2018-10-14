
function login() {
    $.ajax({
        type:"post",
        url:"/accountCheck",
        data:$("#loginAjax").serialize(),
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function (data) {
            if (data.status == 200) {
                console.log("200 execute");
                $("#tip")[0].innerHTML = data.info;
                var form = document.getElementById("loginAjax");
                form.submit();
                console.log("200 execute");
            } else {
                console.log("404 execute");
                $("#tip")[0].innerHTML = data.info;
            }
        },
        error:function (data) {
            $("#tip")[0].innerHTML = "<div class=\"alert alert-danger\">\n" +
                "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n" +
                "    <strong>ERROR: </strong> login() execute error body" +
                "</div>"
        }
    });
    return false;
}