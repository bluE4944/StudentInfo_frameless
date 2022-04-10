// var xmlhttp;
var localIp = "192.168.1.106";
function setCookie(cname,cvalue)
{
    var d = new Date();
    var day = 1; //天
    var hour = 24; //小时
    var minute = 60; //分
    var s = 60; //秒
    d.setTime(d.getTime()+(day*hour*minute*s*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";expire=" + expires+";domain="+localIp+";path=/";
}

window.onload = function () {
    var jsonData = {
        "set_operate":"getLoginName"
    };
     $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/setting", //向服务器请求的url
        data:jsonData, //注意，就算不需要发送数据给后端也要有data
        dataType:"text",
        success:function (data) {
            if(data!="") {
                document.getElementById("login_show").innerHTML = data;
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
};

function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function cookieToJson(phoneNumber,password) {
    //把两个cookie转Json
    var jsondata={
        "phoneNumber":phoneNumber,
        "passWord":password
    };
    return jsondata;
}

function getCookieToJson() {
    //获得 Cookie转成Json 的数据
    var phoneNumber = getCookie("phoneNumber");
    var passWord = getCookie("passWord");
    return cookieToJson(phoneNumber,passWord);
}

function remember() {
    var patt = /^\d{11}$/;
    if(document.getElementById("userid").value==""){
        $("#userid").addClass("is-invalid");
        return ;
    }else if(!patt.test(document.getElementById("userid").value)){
        document.getElementById("phoneMsg").innerHTML = "请输入正确格式的电话！";
        $("#userid").addClass("is-invalid");
        return ;
    }else {
        $("#userid").removeClass("is-invalid");
    }
    if(document.getElementById("password").value==""){
        $("#password").addClass("is-invalid");
        return ;
    }else {
        $("#password").removeClass("is-invalid");
    }
    //设置两个cookie
    setCookie("phoneNumber",document.getElementById("userid").value);
    setCookie("passWord",document.getElementById("password").value);
    //获得数据
    var jsonData = getCookieToJson();
    loadXMLDoc(jsonData);
}

/**
 * 表点换行登录
 */
$("#login_from").keydown(function(e) {
    if (e.keyCode == 13) {
        remember();
    }
});

function loadXMLDoc(jsonData) {
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/login", //向服务器请求的url
        data:jsonData, //注意，就算不需要发送数据给后端也要有data
        dataType:"text",
        success:function (data) {
            if(data!=""){
                document.getElementById("phoneMsg").innerHTML = "";
                document.getElementById("passwordMsg").innerHTML = data;
                $("#userid").addClass("is-invalid");
                $("#password").addClass("is-invalid");
                return;
            }
            window.location.href = "http://"+localIp+":8080/Server/page/welcome.jsp";
        },
        error:function (e) {
            alert("发生错误");
        }
    })

    // if(window.XMLHttpRequest){
    //     //code for IE7+,Firefox,Chrome,Opera,Safari
    //     xmlhttp = new XMLHttpRequest();
    // }else{
    //     // code for IE6,IE5
    //     xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    // }
    // xmlhttp.onreadystatechange=function () {
    //     if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    //         //请求成功
    //         if (xmlhttp.responseText== "1") {
    //             document.getElementById("respMsg").innerHTML = "您的账号或密码错误！";
    //             return;
    //         }
    //         window.location.href = "http://localhost:8080/Server/page/welcome.jsp";
    //     }
    //     var data = "phoneNumber=" + phoneNumber + "&passWord=" + passWord;
    //     xmlhttp.open("POST", "/Server/login", true);
    //     xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    //     xmlhttp.send(data);
    // }
}