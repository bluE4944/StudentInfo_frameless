
/**
 * 根据参数修改名称
 * @param set_operate
 */
function setName(set_operate) {
    var name ;
    var oldPassword;
    var newPassword;
    switch (set_operate) {
        case "setSystemName":
            name = document.getElementById("inputSystemName").value;
            if(name==""){
                $("#inputSystemName").addClass("is-invalid");
                return;
            }else {
                $("#inputSystemName").removeClass("is-invalid");
            }
            break;
        case "setLoginName":
            name = document.getElementById("inputLoginName").value;
            if(name==""){
                $("#inputLoginName").addClass("is-invalid");
                return;
            }else {
                $("#inputLoginName").removeClass("is-invalid");
            }
            break;
        case "setNavName":
            name = document.getElementById("inputNavName").value;
            if(name==""){
                $("#inputNavName").addClass("is-invalid");
                return;
            }else {
                $("#inputNavName").removeClass("is-invalid");
            }
            break;
        case "setPassword":
            oldPassword = document.getElementById("oldPassword").value;
            newPassword = document.getElementById("newPassword").value;
            break;
        default:
            break;
    }
    if(set_operate=="setPassword"){
        if(oldPassword==""&&newPassword!=""){
            $("#oldPassword").addClass("is-invalid");
            $("#newPassword").removeClass("is-invalid");
            return;
        }else if(newPassword==""&&oldPassword!=""){
            $("#newPassword").addClass("is-invalid");
            $("#oldPassword").removeClass("is-invalid");
            return;
        }else if(newPassword==""&&oldPassword==""){
            $("#newPassword").addClass("is-invalid");
            $("#oldPassword").addClass("is-invalid");
            return;
        }else {
            $("#newPassword").removeClass("is-invalid");
            $("#oldPassword").removeClass("is-invalid");
        }
        var jsonData = {
            "set_operate":set_operate,
            "oldPassword":oldPassword,
            "newPassword":newPassword
        }
    }else {
        var jsonData = {
            "set_operate":set_operate,
            "name":name
        };
    }
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/setting", //向服务器请求的url
        data:jsonData, //注意，就算不需要发送数据给后端也要有data
        dataType:"text",
        success:function (data) {
            if(set_operate=="setPassword"){
                alert(data);
            }else {
                alert("修改成功，刷新页面即可生效");
            }
        },
        error:function (e) {
            alert("发生错误");
        }
    })

}

function getAllName() {
    var jsonData = {
        "set_operate":"getAllName"
    };
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/setting", //向服务器请求的url
        data:jsonData, //注意，就算不需要发送数据给后端也要有data
        dataType:"JSON",
        success:function (data) {
            if(data!="") {
                document.getElementById("navName").value = data.navName;
                document.getElementById("oldSystemName").value =data.systemName;
                document.getElementById("loginName").value = data.loginName;
            }else {
                //我觉得应该不会出现这种情况
                //吧？
                alert("data为空！");
            }
        },
        error:function (e) {
            alert("发生错误");
        }
    })
}