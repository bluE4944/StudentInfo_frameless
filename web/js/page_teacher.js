


function teacAlter(operate) {
    //传给后端的数据
    var jsonData = {};

    var teac_name = "";
    teac_name = document.getElementById("inputTeac_name").value;

    var teac_phoneNumber  = "";
    teac_phoneNumber  = document.getElementById("inputTeac_phoneNumber").value;

    var teac_address ="";
    teac_address  = document.getElementById("inputTeac_address").value;

    if(operate=="save"){
        //保存
        if(teac_name=="") {
            alert("姓名不能为空！");
            return;
        }else if (teac_phoneNumber==""){
            alert("电话不能为空！");
            return;
        }
        jsonData = {
            "teac_name":teac_name,
            "teac_phoneNumber":teac_phoneNumber,
            "teac_address":teac_address
        };
        //清空输入框的值
        cleared();
    }else if(operate=="delete"){
        //删除
        var checkId = [] ;
        //计数
        var count=0;
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
                var teac_id = "teac_id"+i;
                jsonData[teac_id] = $(checkId[i]).attr('teac_id');
            }
            // for(var k in jsonData ){//遍历packJson 对象的每个key/value对,k为key
            //     alert(k + " " + jsonData[k]);
            // }
        }
    }else {
        //查看数据
        jsonData = {
            "pageNum":pageNum,
            "pageSize":pageSize
        };

    }
    jsonData["operate"] = operate;

    //发送数据
    teac_jdbc(jsonData);
}


/**
 * 构造换页a标签
 */
function createTeacherA(){
    var selectPage;

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
                teac_pageTurning(2);
            }else if($(this).attr("aria-label") == "Next"){
                //下一页
                teac_pageTurning(3);
            }
        }else {
            //包含数字 指哪跳哪
            pageNum = selectPage;
            //加载所有数据的表格
            teac_pageTurning(0);
        }
    });
}

/**
 * 页面调换 {1，2，3，4,5,6}
 * 1表示首页 2表示上一页 3表示下一页 4表示尾页 5表示GO跳转到指定页面 6表示 查看按钮
 * @param btnNumber
 */
function teac_pageTurning(btnNumber) {
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
            break;
        default :
            break;
    }
    //加载所有数据的表格
    teacAlter('findAll');
}

/**
 * 向服务器更新数据库
 * @param jsondata
 */
function teac_jdbc(jsondata) {

    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/teacher", //向服务器请求的url
        data:jsondata, //注意，就算不需要发送数据给后端也要有data
        //dataType:"JSON",
        // //ajax2.0可以不用设置请求头，但是jq帮我们自动设置了，这样的话需要我们自己取消掉
        // contentType:false,
        // //取消帮我们格式化数据，是什么就是什么
        // processData:false,
        success:function (data) {

            //成功返回后
            //获得数据
            if(data!=""){
                data=eval('('+data+')');//这段代码就是将字符串转换成json格式
                this.pageNum = parseInt(data.pageNum);
                viewPage = parseInt(data.viewPage);
                totalRecord = parseInt(data.totalRecord);
                totalPage =parseInt(data.totalPage);
                viewstart = parseInt(data.start);
                viewend = parseInt(data.end);
                createTeacherTable(data);
            }else {
                teac_pageTurning(6);
            }
        },
        error:function (e) {
            //在切换页面的时候有错误
            alert(e.readyState);
            alert(e.status );
            alert(e.statusText  );
            alert(e.responseText);
            alert("teac_jdbc发生错误");
        }
    });
}

/**
 * 创建表格
 * @param data
 */
function createTeacherTable(data) {
    pageData = [];
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
        var msg = "<tr onclick='checkboxTrue(this)' >"+
            "<td class='align-self-center'> <input type=\"checkbox\"  teac_id='"+ data.list[i].teac_id +"' id='check"+i+"' /></td>"+
            "<th scope=\"row\">"+data.list[i].teac_id+"</th>"+
            "<td>"+data.list[i].teac_name+"</td>"+
            "<td>"+data.list[i].teac_phoneNumber+"</td>"+
            "<td>"+data.list[i].teac_address+"</td>"+
            "</tr>";
        //添加到数组中
        pageData.push(msg);
    }
    document.getElementById("totalMsg").innerHTML="共"+totalPage+"页"+" "+totalRecord+"条记录";
    //创建换页标签
    createTeacherA();
    //获得表格
    //清除表格内容
    document.getElementById("table").innerHTML = "";
    //构造表格
    for(var i = 0 ;i<pageLength;i++){
        $('#table').append(pageData[i]);
    }
    //判断 div是否显示
    var btnDisplay = document.getElementById("btnTurning").style.display;
    if(btnDisplay==""){
        //未显示 让它显示
        document.getElementById("btnTurning").style.display="flex";
        document.getElementById("goPageDiv").style.display = "flex";
    }
}