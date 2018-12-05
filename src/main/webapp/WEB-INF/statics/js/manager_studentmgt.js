function getStudentById() {
    var searchText = $("#searchText")[0].value;
    $.ajax({
        type:"post",
        url:"/getStudentById",
        data: {stu_id: searchText},
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

function getStudentsByName() {
    var searchText = $("#searchText")[0].value;
    $.ajax({
        type:"post",
        url:"/getStudentsByName",
        data: {stu_name: searchText},
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
function getUpdateStudentForm(stu_id) {
    $.ajax({
        type:"post",
        url:"/getStudentUpdateForm",
        data: {stu_id: stu_id},
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

function getAddStudentForm() {
    $.ajax({
        type:"get",
        url:"/getStudentAddForm",
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

function updateStudent() {
    $.ajax({
        type:"post",
        url:"/updateStudent",
        data:$("#studentForm").serialize(),
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

function addNewStudent() {
    $.ajax({
        type:"post",
        url:"/addNewStudent",
        data:$("#studentForm").serialize(),
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