//接收后台传来的teacher数组
var teacher = [];

//接收后台传来的clazz数组
var clazz = [];

function autoDivHeight() { //函数：获取尺寸
    //获取浏览器窗口高度
    var winHeight = 0;
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;
    //通过深入Document内部对body进行检测，获取浏览器窗口高度
    if (document.documentElement && document.documentElement.clientHeight)
        winHeight = document.documentElement.scrollHeight;
    //DIV高度为浏览器窗口高度的一半
    //document.getElementById("test").style.height= winHeight/2 +"px";
    //DIV高度为浏览器窗口高度
    document.getElementById("page_left").style.height = winHeight + "px";
    //获取right_top高度
    var topHeight = document.getElementById("right_top").style.height;
    var downHeight = parseFloat(winHeight) - parseFloat(topHeight);
    //div高度为窗口高度减去top高度
    document.getElementById("right_down").style.height = downHeight + "px";
}


/**
 * navigator 对象包含有关浏览器的信息。
 * userAgent 返回由客户机发送服务器的 user-agent（用户代理） 头部的值。
 * @returns {boolean}
 * @constructor
 */
// function IsPC() {
//     var userAgentInfo = navigator.userAgent;
//     var Agents = ["Android", "iPhone",
//         "SymbianOS", "Windows Phone",
//         "iPad", "iPod"];
//     var flag = true;
//     for (var v = 0; v < Agents.length; v++) {
//         if (userAgentInfo.indexOf(Agents[v]) > 0) {
//             flag = false;
//             break;
//         }
//     }
//     return flag;
// }
//
// var flag = IsPC(); //true为PC端，false为手机端


// //添加数据到数组中
// teac_id.push(data.list[i].teac_id);
// teac_name.push(data.list[i].teac_name);
/**
 * 根据窗口判断 导航是否显示
 */
function navNeedShow(){
    var winWidth = parseFloat(document.body.clientWidth);
    if(winWidth<900){
        $("#nav_show").attr("navShow","false");
        document.getElementById("fa_bars").className = "fa fa-chevron-circle-right";
        if(IsPC()){
            if(winWidth<700){
                document.getElementById("page_left").style.display = "none";
                $("#page_right").css("margin-left","0px");
                $("#page_left").css("box-shadow","10px 0px 50px rgba(0,0,0,0.4)");
                return;
            }
            //电脑端 显示图标
            //设置导航样式 宽
            document.getElementById("page_left").className = "width50";
            //不显示文字
            $('#page_left span').css("display","none");
        }else {
            //移动端 只显示内容，导航栏完全隐藏
            document.getElementById("page_left").style.display = "none";
        }
    }else if(winWidth>1300){
        $("#nav_show").attr("navShow","true");
        $('#page_left span').css("display","inline-block");
        //不显示 点× 关闭
        document.getElementById("onNav").style.display = "none";
        //改变图标样式
        document.getElementById("fa_bars").className = "fa fa-chevron-circle-left";
        //显示导航栏
        document.getElementById("page_left").style.display = "block";
        //显示内容
        document.getElementById("page_right").style.display = "block";
        //设置导航样式 宽
        document.getElementById("page_left").className = "width240";
    }else {
        document.getElementById("page_left").className = "width50";
        //不显示文字
        $('#page_left span').css("display","none");
    }
    if(IsPC()) {
        var navIsShow = $("#nav_show").attr("navShow");
        //电脑端
        if(navIsShow=="true"||navIsShow==undefined){
            //导航栏显示的时候 才添加Margin
            document.getElementById("page_right").style.marginLeft = $("#page_left").css("width");
            $("#page_left").css("box-shadow","");
        }
    }else {
        //手机端
        $("#page_right").css("margin-left","0px");
        $("#page_left").css("box-shadow","10px 0px 50px rgba(0,0,0,0.4)");
    }
}

//窗口改变时做的事
window.onresize=function (){
    autoDivHeight();//浏览器窗口发生变化时同时变化DIV高度
    navNeedShow(); //浏览器窗口发生变化时 检查是否隐藏显示导航栏
};


// window.onresize=function(){
//     document.getElementById("page_left").style.height=document.body.clientHeight+"px";
// }

//窗口加载时 做的事
window.onload = function () {
    //导航栏是否显示
    navNeedShow();
    //获得用户名
    getUserName();
    //自动宽度
    autoDivHeight();
    //加载welcome 页面
    loadXMLDoc("welcome");
};

/**
 * 点击关闭导航栏
 */
$("#nav_show").click(function () {
    var winWidth = parseFloat(document.body.clientWidth);
    var navIsShow =  $("#nav_show").attr("navShow");
    if(navIsShow!="false"){
        //已显示 ，关闭
        $("#nav_show").attr("navShow","false");
        document.getElementById("fa_bars").className = "fa fa-chevron-circle-right";
        document.getElementById("onNav").style.display = "none";
        if(IsPC()){
            //改变样式
            document.getElementById("page_left").className = "width50";
            //不显示文字
            $('#page_left span').css("display","none");
            //电脑端 右边div marginLeft 随导航栏宽度变化
            document.getElementById("page_right").style.marginLeft = $("#page_left").css("width");
        }else {
            document.getElementById("page_left").style.display = "none";
            $("#page_right").css("margin-left","0px");
        }

    }else{
        //关闭状态，显示
        document.getElementById("page_left").style.display = "block";
        $('#page_left span').css("display","inline-block");
        $("#nav_show").attr("navShow","true");
        //很小，需要让div占据更多的空间
        if (winWidth < 650) {
            document.getElementById("page_left").className = "col-8";
            document.getElementById("onNav").style.display = "inline-block";
            $("#page_right").css("margin-left","0px");
            return;
        } else {
            document.getElementById("fa_bars").className = "fa fa-chevron-circle-left";
            document.getElementById("onNav").style.display = "none";
            document.getElementById("page_left").className = "width240";
        }
        if(IsPC()) {
            document.getElementById("page_right").style.marginLeft = $("#page_left").css("width");
        }
    }

});

//手机端关闭导航按钮点击事件
$("#onNav").click(function () {
    var navIsShow =  $("#nav_show").attr("navShow");
    if(navIsShow!="false"){
        $("#nav_show").attr("navShow","false");
        document.getElementById("page_left").style.display = "none";
        document.getElementById("page_right").style.display = "block";
        $("#page_right").css("margin-left","0px");
    }
});

//li点击后 添加 class
$("#page_left ul").on("click","li",function () {
    $("#page_left ul li").removeClass("active1");
    $(this).addClass("active1");
});

//给a添加class
$("#page_left ul li a").addClass("a_style");

//a点击后 添加class
$("#page_left ul li").on("click","a",function () {
    $("#page_left ul li a").removeClass("a_active");
    $(this).addClass("a_active");
});

//判断是哪个a标签
function toPage(tag) {
    if(tag!=null){
        loadXMLDoc(tag);
    }else {
        alert("page为空")
    }
}

/**
 * 获得系统各名字并显示在对应id上
 */
function loadName(page) {
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
                document.getElementById("left_top").innerHTML = data.navName;
                document.getElementById("systemName").innerHTML =data.systemName;
                if(IsPC()){
                    $('#buttons_latter span').css("display","inline-block");
                }else {
                    $('#buttons_latter span').css("display","none");
                }
                switch (page) {
                    case "teacher":
                        teacAlter('findAll');
                        break;
                    case "staff":
                        pageTurning(6);
                        break;
                    case "welcome":
                        getTeacher();
                        break;
                    case "class":
                        clasAlter('findAll');
                        break;
                    case "student":
                        stuAlter('findAll');
                        break;
                    case "course":
                        //设置subject选项
                        selectSubjectSearch();
                        courAlter('findAll');
                        break;
                    default:
                        break;
                }
            }else {
                //我觉得应该不会出现这种情况
                //吧？
                alert("data为空！");
            }
        },
        error:function (e) {
            alert("发生错误");
        }
    });
}

function getTeacher() {
    var jsonData = {
        "operate":"getTeacher"
    };
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/data", //向服务器请求的url
        data:jsonData, //注意，就算不需要发送数据给后端也要有data
        dataType:"JSON",
        success:function (data) {
            if (data != "") {
                for (var i = 0;i<data.length;i++){
                    teacher.push({teac_id: data[i].teac_id, teac_name: data[i].teac_name});
                }
                getClazz();
            }
        },
        error:function (e) {
            alert("发生错误");
        }
    });
}

function getClazz() {
    var jsonData = {
        "operate":"getClazz"
    };
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/data", //向服务器请求的url
        data:jsonData, //注意，就算不需要发送数据给后端也要有data
        dataType:"JSON",
        success:function (data) {
            if (data != "") {
                for (var i = 0;i<data.length;i++) {
                    clazz.push({clas_id: data[i].clas_id, clas_name: data[i].clas_name, teac_id: data[i].teac_id});
                }
            }
        },
        error:function (e) {
            alert("发生错误");
        }
    });
}


//加载页面 
function loadXMLDoc(page) {
    if (window.XMLHttpRequest) {
        //code for IE7+,Firefox,Chrome,Opera,Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        // code for IE6,IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //请求成功
            document.getElementById("right_down").innerHTML = xmlhttp.responseText;
            loadName(page);
        }
    };
    var data = "page=" + page ;
    xmlhttp.open("POST", "/Server/ajax", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send(data);
}


//cookie相关操作

function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function delCookie(name)
{
    var date = new Date();
    date.setTime(date.getTime()-10000);
    document.cookie = name+"="+"delete"+";expire="+date.toUTCString()+";path=/";
}

function cookieToJson(phoneNumber,password) {
    //把两个cookie转Json
    var jsondata={
        "phoneNumber":phoneNumber,
        "passWord":password,
        "getName":"yes"
    };
    return jsondata;
}

function getCookieToJson() {
    //获得 Cookie转成Json 的数据
    var phoneNumber = getCookie("phoneNumber");
    var passWord = getCookie("passWord");
    return cookieToJson(phoneNumber,passWord);
}
//以上是cookie相关操作

//获得userName 并显示在#login_welcome 里
function getUserName() {
    var jsonData = getCookieToJson();
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/login", //向服务器请求的url
        data:jsonData, //注意，就算不需要发送数据给后端也要有data
        success:function (data) {
            document.getElementById("login_welcome").innerHTML = data;
        },
        error:function (e) {
            alert("getUserName发生错误");
        }
    })
}

//退出登录
function loginOut() {
    //删除Cookie
    delCookie("phoneNumber");
    delCookie("passWord");
}

/**
 * 显示签到
 */
function sign() {
    
}

