

//后端数据
var pageData;
//当前页默认为1
var pageNum = 1;
//一页显示多少条记录
var pageSize= 11;
// 定义的常量 跳转显示 几页 如 1， 2 ， 3 ， 4 ，5
var viewPage ;
//总共有几条数据
var totalRecord;
//共有几页
var totalPage;
/**
 * 分页显示的最前面的页数
 * 如 1 、 2 、 3 、 4 、 5 viewstart为1
 */
var viewstart = 1;
/**
 * 分页显示的最后面的页数
 * 如 1 、 2 、 3 、 4 、 5 viewend为5
 */
var viewend = 5;

//当前页面有几条记录
var pageLength;

//跳转的url
var url;

//userID
var uid = -1;

//username
var uname = "";

//user电话
var uphoneNumber = "";

//管理员
var admin =-1;

var myOperate;


/**
 * navigator 对象包含有关浏览器的信息。
 * userAgent 返回由客户机发送服务器的 user-agent（用户代理） 头部的值。
 * @returns {boolean}
 * @constructor
 */
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}





/**
 * 修改数据库(user表)
 * @param operate 要进行的操作
 */
function userAlter(operate) {
    var jsonData={};

    uid = document.getElementById("input_uid").value;

    uname = document.getElementById("input_uname").value;

    uphoneNumber= document.getElementById("input_uphoneNumber").value;
    if(operate=="update"){
        var checkId ;
        //计数
        var count=0;
        for(var i=0;i<pageLength;i++){
            var boxId = "check"+i;
            if(document.getElementById(boxId).checked == true){
                count++;
                checkId = "#"+boxId;
            }
        }
        if (count>=2){
            //同时选中了两条信息
            alert("修改一次只能选中一条信息！");
            return;
        }else if(uid==""&&count==1){
            //选中了一条信息，没填id
            uid = $(checkId).attr('user_id');
        }else if(count==0&&uid==""){
            //没选信息也没填id
            alert("您没有输入id或是选中一条信息！");
            return;
        }
    }else if(operate=="save"){
        if(uname=="") {
            alert("姓名不能为空！");
            return;
        }else if (uphoneNumber==""){
            alert("电话不能为空！");
            return;
        }
    }else if(operate=="delete"){
        //删除
        var checkId = [] ;
        //计数
        var count=0;
        //获得选中的check id
        for(var i=0;i<pageLength;i++){
            var boxId = "check"+i;
            if(document.getElementById(boxId).checked == true){
                count++;
                checkId.push("#"+boxId);
            }
        }
        if(count==0){
            //没选信息也没填id
            alert("请选择一条信息！");
            return;
        }else {
            for (var i=0;i<count;i++){
                var user_id = "user_id"+i;
                jsonData[user_id] = $(checkId[i]).attr('user_id');
            }
            // for(var k in jsonData ){//遍历packJson 对象的每个key/value对,k为key
            //     alert(k + " " + jsonData[k]);
            // }
        }
    } else{
        var isBlank = uid+uname+uphoneNumber;
        if(isBlank==""){
            alert("您没有输入信息！");
            return;
        }
    }

    //传给后端的数据


    //获取checkbox元素
    var box = document.getElementById("exampleCheck1");

    //选中返回true，未选中返回false
    if(box.checked){
        //是管理员
        admin = 1;
    }else {
        admin = 2;
    }

    //构造json数据
    jsonData["operate"] = operate;
    jsonData["uid"] = uid;
    jsonData["uname"] = uname;
    jsonData["uphoneNumber"] = uphoneNumber;
    jsonData["administer"] = admin;


    url  = "/Server/user";
    //发送数据
    swapData(jsonData ,operate);
}



/**
 * 向服务器更新数据库
 * @param jsondata
 */
function swapData(jsondata,operate) {

    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:url, //向服务器请求的url
        data:jsondata, //注意，就算不需要发送数据给后端也要有data
        dataType:"text",
        // //ajax2.0可以不用设置请求头，但是jq帮我们自动设置了，这样的话需要我们自己取消掉
        // contentType:false,
        // //取消帮我们格式化数据，是什么就是什么
        // processData:false,
        success:function (data) {
            if(data!=""){
                alert(data);
                url = "/Server/page";
                return ;
            }
            if(operate=="select"){
                //只有这个才能返回查询的数据
                myOperate = "selectUser";
                getTebBody(pageNum);
            }else {
                //清空输入框的值
                document.getElementById("input_uid").value="";
                document.getElementById("input_uname").value="";
                document.getElementById("input_uphoneNumber").value="";
                url = "/Server/page";
                getTebBody(pageNum);
            }
        },
        error:function (e) {
            //在切换页面的时候有错误
            alert("swapData发生错误");
        }
    });
}

/**
 * 以下是更新表格的操作
 */

//获得表格 第 pageNum 页
function getTable() {
    //清除表格内容
    document.getElementById("table").innerHTML = "";
    //构造表格
    for(var i = 0 ;i<pageLength;i++){
        $('#table').append(pageData[i]);
    }
}



/**
 * 构造换页a标签
 */
function createA(){
    var selectPage ;

    $('#paging').html('');
    //造个简单的分页按钮
    for (var i = viewstart; i <= viewend; i++) {
        if(i==viewstart){
            pageN ='<li class="page-item">\n' +
                '      <a class="page-link" href="#" aria-label="Previous">\n' +
                '        <span aria-hidden="true">&laquo;</span>\n' +
                '      </a>\n' +
                '    </li>';
            $('#paging').append(pageN);
        }
        if(i==pageNum){
            var pageN = '<li class="page-item disabled"><a class="page-link" href="#" selectPage="' + i + '" > ' + i + ' </a></li>';
            $('#paging').append(pageN);
        }else {
            var pageN = '<li class="page-item"><a class="page-link" href="#" selectPage="' + i + '" > ' + i + ' </a></li>';
            $('#paging').append(pageN);
        }

        if(i==viewend){
            pageN = '<li class="page-item" >\n' +
                '      <a class="page-link" href="#" aria-label="Next">\n' +
                '        <span aria-hidden="true">&raquo;</span>\n' +
                '      </a>\n' +
                '    </li>';
            $('#paging').append(pageN);
        }
    }

    //显示选择页的内容
    $('a').click(function(){
        selectPage =parseInt($(this).attr('selectPage'));
        //是否包含数字
        var patt1=new RegExp("[0-9]");
        if(!patt1.test(selectPage)){
            //不包含数字，前一页或下一页按钮
            if($(this).attr("aria-label") == "Previous"){
                //上一页
                pageTurning(2);
            }else if($(this).attr("aria-label") == "Next"){
                //下一页
                pageTurning(3);
            }
        }else {
            //包含数字 指哪跳哪
            pageNum = selectPage;
            getTebBody(pageNum);
        }
    });
}

/**
 * 页面调换 {1，2，3，4,5,6}
 * 1表示首页 2表示上一页 3表示下一页 4表示尾页 5表示GO跳转到指定页面 6表示 查看按钮
 * @param btnNumber
 */
function pageTurning(btnNumber) {
    //电脑端显示13条数据 手机端显示5条
    var flag = IsPC(); //true为PC端，false为手机端
    if(flag){
        pageSize = 11;
    }else {
        pageSize = 5;
    }

    //转Int
    var btnNumberInt = parseInt(btnNumber);
    //获得输入框的值
    var goPageNum = parseInt(document.getElementById("goPageInp").value);


    //根据按钮做出相应动作
    switch (btnNumberInt) {
        case 1:
            //首页按钮 设置当前页面为1
            if(pageNum==1){
                //已经在首页
                alert("已经是第一页了哟！");
                return ;
            }
            pageNum=1;
            break;
        case 2:
            //上一页按钮 设置当前页面减一
            if(pageNum==1){
                //已经在首页
                alert("已经是第一页了哟！");
                return ;
            }
            pageNum--;
            break;
        case 3:
            //下一页按钮 设置当前页面加一
            if(pageNum==totalPage){
                //当前页面已经是尾页
                alert("已经是最后一页啦！");
                return ;
            }
            pageNum++;
            break;
        case 4:
            //尾页按钮 设置当前页面为总页数
            if(pageNum==totalPage){
                //当前页面已经是尾页
                alert("已经是最后一页啦！");
                return ;
            }
            pageNum = totalPage;
            break;
        case 5:
            //跳转至 goPageNum 页 GO按钮
            if(goPageNum>totalPage||goPageNum<=0){
                //不合法输入
                alert("请输入正确的页数！");
                return;
            }
            //设置当前页为输入 的页数
            pageNum=goPageNum;
            break;
        case 6:
            //查看所有数据 的按钮
            pageNum=1;
            //设置url 能查看所有数据
            url = "/Server/page";
            break;
        default :
            alert("换页出错了");
            break;
    }
    if(url=="/Server/page"){
        //加载所有数据的表格
        getTebBody(pageNum);
    }else {
        //加载查询后的表格
        userAlter("select");
    }

}


/**
 * 从后端获取user数据
 * @param pageNum 获取第几页
 * @param pageSize 显示几条数据
 */
function getTebBody(pageNum) {
    var jsondata;
    //在切换页面的时候会调用 getTebBody 本方法，加个判断让它不往下执行
    //是否包含数字
    var patt1=new RegExp("[0-9]");
    if(!patt1.test(pageNum)){
        //pageNum不包含 0-9 不是换页按钮调用 直接返回
        return ;
    }
    if(myOperate=="selectUser"){
        jsondata={
            "pageNum":pageNum,
            "pageSize":pageSize,
            "operate":myOperate,
            "uid":uid,
            "uname":uname,
            "uphoneNumber":uphoneNumber,
            "administer":admin
        };
    }else {
        jsondata={
            "pageNum":pageNum,
            "pageSize":pageSize
        };
    }

    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:url, //向服务器请求的url
        data:jsondata, //注意，就算不需要发送数据给后端也要有data
        dataType:"JSON",
        success:function (data) {
            //成功返回后
            //获得数据
            this.pageNum = parseInt(data.pageNum);
            viewPage = parseInt(data.viewPage);
            totalRecord = parseInt(data.totalRecord);
            totalPage =parseInt(data.totalPage);
            viewstart = parseInt(data.start);
            viewend = parseInt(data.end);
            pageData = [];
            var isAdminister;

            if(totalRecord%pageSize==0){
                //表示 没有多余的记录 （刚好每页都有 pageSize 条记录 ）
                pageLength=pageSize;
            }else {
                //有多余的记录 （最后一个 比 pageSize 条记录少）
                if(pageNum==totalPage){
                    //最后一页 显示的数据 为 总记录 取 每页正常显示记录 的余数
                    pageLength=totalRecord%pageSize;
                }else{
                    //不是最后一页，正常显示
                    pageLength=pageSize;
                }
            }
            //构建表格
            for(var i=0;i<pageLength;i++){
                admin = data.list[i].administer;
                if (admin==1){
                    isAdminister = "管理员"
                }else{
                    isAdminister = "非管理员";
                }
                var msg = "<tr onclick='checkboxTrue(this)'>"+
                    "<td class='align-self-center'> <input type=\"checkbox\" user_id='"+ data.list[i].id +"' id='check"+i+"' /></td>"+
                    "<th scope=\"row\">"+data.list[i].id+"</th>"+
                    "<td>"+data.list[i].username+"</td>"+
                    "<td>"+data.list[i].phoneNumber+"</td>"+
                    "<td>"+isAdminister+"</td>"+
                    "<td>"+data.list[i].registerTime+"</td>"+
                    "</tr>";
                //添加到数组中
                pageData.push(msg);
            }
            document.getElementById("totalMsg").innerHTML="共"+totalPage+"页"+" "+totalRecord+"条记录";
            //创建换页标签
            createA();
            //获得表格
            getTable();
            //判断 div是否显示
            var btnDisplay = document.getElementById("btnTurning").style.display;
            if(btnDisplay==""){
                //未显示 让它显示
                document.getElementById("btnTurning").style.display="flex";
                document.getElementById("goPageDiv").style.display = "flex";
            }
        },
        error:function (e) {
            //在切换页面的时候有错误
            alert("getTebBody发生错误");
        }
    })
}