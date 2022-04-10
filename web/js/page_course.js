
var subjects = ["结构与力","动力机械Ⅰ","能源世界","动力机械Ⅱ","疯狂小发明","WeDo2.0","机器人基础","海洋世界"];

function selectSubjectSearch() {
    for(var i=0;i<subjects.length;i++){
        $(' select').append("<option value='"+(i+1)+"'> "+subjects[i]+" </option>");
    }
}


function courAlter(operate) {//传给后端的数据
    var jsonData = {};
    var cour_name = "";
    var cour_introduction = "";
    var cour_subject = "";
    if(operate=="save"){
        //保存
        cour_name= document.getElementById("inputCour_name").value;

        cour_subject  = document.getElementById("selectSubjectSave").value;

        cour_introduction  = document.getElementById("cour_introduction").value;
        if(cour_name=="") {
            alert("请填写课程名称！");
            return;
        }else if (cour_subject==0){
            alert("请选择主题！");
            return;
        }
        cour_subject=subjects[cour_subject-1];
        jsonData = {
            "cour_name":cour_name,
            "cour_subject":cour_subject,
            "cour_introduction":cour_introduction
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
                var cour_id = "cour_id"+i;
                jsonData[cour_id] = $(checkId[i]).attr('cour_id');
            }
            // for(var k in jsonData ){//遍历packJson 对象的每个key/value对,k为key
            //     alert(k + " " + jsonData[k]);
            // }
        }
    }else if(operate=="Search"){
        cour_subject = document.getElementById("selectSubject").value;
        if(cour_subject==0){
            return;
        }
        cour_subject = subjects[cour_subject-1];
        //查看数据
        jsonData = {
            "cour_subject":cour_subject,
            "pageNum":pageNum,
            "pageSize":pageSize
        };
    } else {
        //查看数据
        jsonData = {
            "pageNum":pageNum,
            "pageSize":pageSize
        };
    }
    jsonData["operate"] = operate;

    //发送数据
    cour_jdbc(jsonData);
}


/**
 * 向服务器更新数据库
 * @param jsondata
 */
function cour_jdbc(jsondata) {
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/course", //向服务器请求的url
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
                createCourseTable(data);
            }else {
                cour_pageTurning(6);
            }
        },
        error:function (e) {
            //在切换页面的时候有错误
            alert(e.readyState);
            alert(e.status );
            alert(e.statusText  );
            alert(e.responseText);
            alert("cour_jdbc发生错误");
        }
    });
}


/**
 * 创建表格
 * @param data
 */
function createCourseTable(data) {
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
    if(data.list.length<=0){
        document.getElementById("table").innerHTML = "";
        document.getElementById("totalMsg").innerHTML = "共0页 0条记录";
        return;
    }
    //构建表格
    for(var i=0;i<pageLength;i++){

        var teac_names = teacher.filter((p) => {
            return p.teac_id == data.list[i].teac_id;
        });
        var msg = "<tr onclick='checkboxTrue(this)' >"+
            "<td class='align-self-center'> <input type=\"checkbox\" cour_id='"+ data.list[i].cour_id +"' id='check"+i+"' /></td>"+
            "<th scope=\"row\">"+data.list[i].cour_id+"</th>"+
            "<td>"+data.list[i].cour_name+"</td>"+
            "<td>"+data.list[i].cour_subject+"</td>"+
            "<td>"+data.list[i].cour_introduction+"</td>"+
            "</tr>";
        //添加到数组中
        pageData.push(msg);
    }
    document.getElementById("totalMsg").innerHTML="共"+totalPage+"页"+" "+totalRecord+"条记录";
    //创建换页标签
    createCourseA();
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
        $("#btnTurning").removeClass("displayNone");
        $("#goPageDiv").removeClass("displayNone");
    }
}

/**
 * 构造换页a标签
 */
function createCourseA(){
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
                cour_pageTurning(2);
            }else if($(this).attr("aria-label") == "Next"){
                //下一页
                cour_pageTurning(3);
            }
        }else {
            //包含数字 指哪跳哪
            pageNum = selectPage;
            //默认什么也不做
            cour_pageTurning(0);
        }
    });
}

/**
 * 页面调换 {1，2，3，4,5,6}
 * 1表示首页 2表示上一页 3表示下一页 4表示尾页 5表示GO跳转到指定页面 6表示 查看按钮
 * @param btnNumber
 */
function cour_pageTurning(btnNumber) {
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
    if(IsSearch()){
        courAlter('Search');
    }else {
        //加载所有数据的表格
        courAlter('findAll');
    }

}

