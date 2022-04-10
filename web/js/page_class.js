


function selectTeacher() {
    for (var i=0;i<teacher.length;i++){
        $("#selectTeacherSave").append("<option value='"+teacher[i].teac_id+"'>"+teacher[i].teac_name+"</option>");
        $("#selectTeacher").append("<option value='"+teacher[i].teac_id+"'>"+teacher[i].teac_name+"</option>");
        $("#editTeacher").append("<option value='"+teacher[i].teac_id+"'>"+teacher[i].teac_name+"</option>");
    }
}

function selectClass(search) {
    $("#editClass").html('');
    $("#selectClassSave").html('');
    $("#selectClass").html('');
    for (var i=0;i<clazz.length;i++){
        if(document.getElementById("selectTeacherSave").value==clazz[i].teac_id){
            $("#selectClassSave").append("<option value='"+clazz[i].clas_id+"'>"+clazz[i].clas_name+"</option>");
            $("#selectClass").append("<option value='"+clazz[i].clas_id+"'>"+clazz[i].clas_name+"</option>");
        }
        if(search!=undefined){
            if(document.getElementById("editTeacher").value==clazz[i].teac_id){
                $("#editClass").append("<option value='"+clazz[i].clas_id+"'>"+clazz[i].clas_name+"</option>");
            }
        }

    }
}

function initLaydate(){
    laydate.render({
        elem: document.getElementById('class_time') //指定元素
        ,type: 'time'
    });
}


function clasAlter(operate) {//传给后端的数据
    var jsonData = {};

    if(operate=="save"){
        //保存
        var clas_name = "";
        clas_name= document.getElementById("inputClass_name").value;

        var teac_id = 0;
        teac_id  = document.getElementById("selectTeacherSave").value;

        var clas_time = "";
        clas_time  = document.getElementById("class_time").value;

        var clas_week = 0;
        clas_week = document.getElementById("class_week").value;

        if(clas_name=="") {
            alert("请填写班级名称！");
            return;
        }else if (teac_id==0){
            alert("请选择老师！");
            return;
        }else if(clas_time=="") {
            alert("请选择时间！");
            return;
        }else if(clas_week == 0){
            alert("请选择周几！");
            return;
        }
        jsonData = {
            "clas_name":clas_name,
            "teac_id":teac_id,
            "clas_time":clas_time,
            "clas_week":clas_week
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
                var clas_id = "clas_id"+i;
                jsonData[clas_id] = $(checkId[i]).attr('clas_id');
            }
            // for(var k in jsonData ){//遍历packJson 对象的每个key/value对,k为key
            //     alert(k + " " + jsonData[k]);
            // }
        }
    }else if(operate=="Search"){
        teac_id = document.getElementById("selectTeacher").value;
        if(teac_id==0){
            return;
        }
        //查看数据
        jsonData = {
            "teac_id":teac_id,
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
    clas_jdbc(jsonData);
}

/**
 * 向服务器更新数据库
 * @param jsondata
 */
function clas_jdbc(jsondata) {
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/clazz", //向服务器请求的url
        data:jsondata, //注意，就算不需要发送数据给后端也要有data
        //dataType:"JSON",
        // //ajax2.0可以不用设置请求头，但是jq帮我们自动设置了，这样的话需要我们自己取消掉
        // contentType:false,
        // //取消帮我们格式化数据，是什么就是什么
        // processData:false,
        success:function (data) {
            //设置 select 的选项
            selectTeacher();
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
                createClazzTable(data);
            }else {
                clas_pageTurning(6);
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
 * 把int 类型的转换成中文类型的周数
 * @param week
 * @returns {string}
 */
function toChinWeek(week) {
    var chineWeek;
    switch (week) {
        case 1:
            chineWeek = "周一";
            break;
        case 2:
            chineWeek = "周二";
            break;
        case 3:
            chineWeek = "周三";
            break;
        case 4:
            chineWeek = "周四";
            break;
        case 5:
            chineWeek = "周五";
            break;
        case 6:
            chineWeek = "周六";
            break;
        case 7:
            chineWeek = "周日";
            break;
        default:
            break;
    }
    return chineWeek;
}

/**
 * 创建表格
 * @param data
 */
function createClazzTable(data) {
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
            "<td class='align-self-center'> <input type=\"checkbox\" clas_id='"+ data.list[i].clas_id +"' id='check"+i+"' /></td>"+
            "<th scope=\"row\">"+data.list[i].clas_id+"</th>"+
            "<td>"+data.list[i].clas_name+"</td>"+
            "<td>"+teac_names[0].teac_name+"</td>"+
            "<td>"+toChinWeek(data.list[i].clas_week)+data.list[i].clas_time+"</td>"+
            "</tr>";
        //添加到数组中
        pageData.push(msg);
    }
    document.getElementById("totalMsg").innerHTML="共"+totalPage+"页"+" "+totalRecord+"条记录";
    //创建换页标签
    createClassA();
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

/**
 * 构造换页a标签
 */
function createClassA(){
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
                clas_pageTurning(2);
            }else if($(this).attr("aria-label") == "Next"){
                //下一页
                clas_pageTurning(3);
            }
        }else {
            //包含数字 指哪跳哪
            pageNum = selectPage;
            //默认什么也不做
            clas_pageTurning(0);
        }
    });
}


/**
 * 页面调换 {1，2，3，4,5,6}
 * 1表示首页 2表示上一页 3表示下一页 4表示尾页 5表示GO跳转到指定页面 6表示 查看按钮
 * @param btnNumber
 */
function clas_pageTurning(btnNumber) {
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
        clasAlter('Search');
    }else {
        //加载所有数据的表格
        clasAlter('findAll');
    }

}