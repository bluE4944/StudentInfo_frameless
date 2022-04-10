
var isEdit = false;

var student = [];

//查询
function searchKeydown(stu_attribute) {
    if(event.keyCode==13){
        alert("searchKeydown");
    }
}

/**
 * 获得选中的check id
 * @returns {[]}
 */
function getCheckId() {
    var checkId = [] ;
    for(var i=0;i<pageLength;i++){
        var boxId = "check"+i;
        if(document.getElementById(boxId).checked == true){
            //选中的id 名 存入数组
            checkId.push("#"+boxId);
        }
    }
    return checkId;
}

/**
 * 根据checkId 改变对应行的内容 成可修改样式
 * @param checkId
 */
function editWithCheckId(checkId) {
    var stuMsg = student.filter((p) => {
        return p.stu_id == $(checkId).attr("stu_id");
    });
    //改变样式
    $(checkId).parent().parent().html(
        "<td class='align-self-center'> <input type=\"checkbox\" stu_id='"+ stuMsg[0].stu_id +"' id='"+checkId.split('#').join('')+"'/></td>"+
        "<th scope=\"row\" id='editStu_Id'>"+stuMsg[0].stu_id+"</th>"+
        "<td> <input type='text' id='editStu_name' class='form-control-plaintext' value='"+stuMsg[0].stu_name+"' /></td>"+
        "<td> <select id='editStu_sex' class='form-control'> <option value='1'>男</option> <option value='2'> 女</option> </select></td>"+
        "<td> <input type='text' id='editStu_age' class='form-control-plaintext' value='"+stuMsg[0].stu_age+"' /></td>"+
        "<td> <input type='number' id='editStu_remainPeriod' class='form-control-plaintext' value='"+stuMsg[0].stu_remainPeriod+"' /></td>"+
        "<td> <input type='number' id='editStu_totalPeriod' class='form-control-plaintext' value='"+stuMsg[0].stu_totalPeriod+"' /></td>"+
        "<td> <select id='editTeacher' class='form-control'  onchange='selectClass(\"search\")'> </select></td>"+
        "<td> <select id='editClass' class='form-control'> </select></td>"
    );
    document.getElementById(checkId.split('#').join('')).checked = true;
    $(" tr").removeAttr("onclick");
    //设置其他按钮不能点击
    $("#buttons_latter button").attr("disabled","disabled");
    $("#stu_edit").removeAttr("disabled");
    //设置老师选项
    selectTeacher();
    selectClass("search");
    //设置学生性别选项
    if(stuMsg[0].stu_sex=="男"){
        $("#editStu_sex").attr("value",1);
    }else {
        $("#editStu_sex").attr("value",2);
    }
    //设置两个选项原来的值
    $("#editTeacher").attr("value",stuMsg[0].teac_id);
    $("#editClass").attr("value",stuMsg[0].clas_id);
}

/**
 * 获得修改后 的json数据
 * @returns {{stu_id: string, stu_remainPeriod: *, stu_sex: (string), stu_age: *, stu_name: *, stu_totalPeriod: *, clas_id: *}}
 */
function getEditData() {
    var stu_id = document.getElementById("editStu_Id").innerText;
    var stu_sex = document.getElementById("editStu_sex").value==1?"男":"女";
    var stu_name = document.getElementById("editStu_name").value;
    var stu_age = document.getElementById("editStu_age").value;
    var stu_remainPeriod = document.getElementById("editStu_remainPeriod").value;
    var stu_totalPeriod = document.getElementById("editStu_totalPeriod").value;
    var clas_id = document.getElementById("editClass").value;

    if(stu_name==""){
        alert("请填写姓名");
        return undefined;
    }else if(stu_remainPeriod<0||stu_totalPeriod<0){
        alert("请输入正确的课时");
        return undefined;
    }else if(stu_age==""){
        alert("请填写年龄");
        return undefined;
    }

    var jsonData = {
        "stu_id":stu_id,
        "stu_sex":stu_sex,
        "stu_name":stu_name,
        "stu_remainPeriod":stu_remainPeriod,
        "stu_totalPeriod":stu_totalPeriod,
        "stu_age":stu_age,
        "clas_id":clas_id
    };
    return jsonData;
}

/**
 * 是否为修改
 * @returns {boolean}
 * @constructor
 */

function IsEdit() {
    //取反
    isEdit = !isEdit;
    return isEdit;
}

/**
 * 显示查询与否
 */
function showSearch() {

    IsSearch
    var trSearchDisplay = document.getElementById("stu_tr_Search").style.display;
    if(trSearchDisplay =="none"){
        document.getElementById("stu_tr_Search").style.display="table-row";
    }else {
        document.getElementById("stu_tr_Search").style.display="none";
    }
}

//清空输入框的内容
function cleared() {
    $("#right_down input").val('');
    $("#right_down select").val(0);
}

/**
 * 判断 obj 是否选中
 * @param obj
 */
function checkboxTrue(obj) {
    var chks = $("input[type='checkbox']",obj);
    var tag = $(obj).attr("tag");
    if(tag=="selected"){
        // 之前已选中，设置为未选中
        $(obj).attr("tag","");
        chks.prop("checked",false);
        $(obj).removeAttr("class")
    }else{
        // 之前未选中，设置为选中
        $(obj).attr("class","table-primary");
        $(obj).attr("tag","selected");
        chks.prop("checked",true);
    }
    //判断是否全部选中，如果全选 就自动勾上全选
    var box =true;
    var checkeId ;
    for(var i=0;i<pageLength;i++){
        checkeId = "check"+i;
        if(document.getElementById(checkeId).checked == false){
            box=false;
        }
    }
    document.getElementById("all").checked = box;
}

/**
 * 全选
 */
function checkAll() {
    //获取checkbox元素
    var box = document.getElementById("all");
    var checkeId ;
    if(box.checked){
        //选中，则全选
        for(var i=0;i<pageLength;i++){
            checkeId = "check"+i;
            document.getElementById(checkeId).checked = true;
            $("#"+checkeId).parent().parent().attr("tag","selected");
            $("#"+checkeId).parent().parent().attr("class","table-primary");
        }
    }else {
        //取消选中，则取消全选
        for(var i=0;i<pageLength;i++){
            checkeId = "check"+i;
            document.getElementById(checkeId).checked = false;
            $("#"+checkeId).parent().parent().attr("tag","");
            $("#"+checkeId).parent().parent().removeAttr("class")
        }
    }
}

function ageFormat(age) {
    //正则表达式 是否包含小数点
    var objRegExp = /^\d+\.\d+$/;
    //包含全数字
    var isNumber = /^\d+$/;

    var isNull = age==""?true:false;
    var isOk = objRegExp.test(age)||isNumber.test(age)||isNull;
    return isOk;
}

/**
 * 跟后端交互
 * @param operate
 */
function stuAlter(operate) {//传给后端的数据
    var jsonData = {};
    var stu_age = 0.0;
    var stu_sex = "";
    var stu_name = "";
    var clas_id = 0;
    //总课时
    var stu_totalPeriod = 0;
    //剩余课时
    var stu_remainPeriod = 0;

    if(operate=="save"){
        //保存
        stu_name= document.getElementById("inputStu_name").value;

        stu_age =  document.getElementById("inputStu_age").value;

        var teac_id = document.getElementById("selectTeacherSave").value;

        clas_id  = document.getElementById("selectClassSave").value;

        stu_sex = document.getElementById("selectStu_sex").value;

        if(stu_name=="") {
            alert("请填写学生姓名！");
            return;
        }else if(!ageFormat(stu_age)){
            alert("请正确填写年龄！");
            return;
        }else if(stu_sex==0){
            alert("请选择性别！");
            return;
        }else if (teac_id==0){
            alert("请选择老师！");
            return;
        }else if(clas_id==0) {
            alert("请选择班级！");
            return;
        }
        stu_sex = document.getElementById("selectStu_sex").value==1?"男":"女";

        jsonData = {
            "stu_name":stu_name,
            "clas_id":clas_id,
            "stu_age":stu_age,
            "stu_sex":stu_sex
        };
        //清空输入框的值
        cleared();
    }else if(operate=="delete"){
        //删除
        if(getCheckId().length<=0){
            //没选信息也没填id
            alert("请选择一条信息！");
            return;
        }else {
            for (var i=0;i<getCheckId().length;i++){
                var stu_id = "stu_id"+i;
                jsonData[stu_id] = $(getCheckId()[i]).attr('stu_id');
            }
            // for(var k in jsonData ){//遍历packJson 对象的每个key/value对,k为key
            //     alert(k + " " + jsonData[k]);
            // }
        }
    }else if(operate=="edit"){
        //修改
        if(getCheckId().length<=0||getCheckId().length>=2){
            //没选信息
            alert("请选择一条信息！");
            return;
        }

        if(IsEdit()){
            //改变成保存的样式
            $("#stu_edit").attr("class","btn btn-primary");
            $("#stu_edit i").attr("class","fa fa-save");
            $("#stu_edit span").text(" 保存 ");
            //根据id改成可修改的
            editWithCheckId(getCheckId()[0]);
            return;
        }else {
            //改回修改的样式
            $("#stu_edit").attr("class","btn btn-info");
            $("#stu_edit i").attr("class","fa fa-edit");
            $("#stu_edit span").text(" 修改 ");
            //移除按钮的disabled属性
            $("#buttons_latter button").removeAttr("disabled");
            //给表头添加点击
            $("#stu_th").attr("onclick","showSearch()");
        }
        if(getEditData()==undefined){
            return;
        }
        jsonData = getEditData();
    } else {
        //查看数据
        jsonData = {
            "pageNum":pageNum,
            "pageSize":pageSize
        };
    }
    jsonData["operate"] = operate;
    //发送数据
    stu_jdbc(jsonData);
}

/**
 * 向服务器更新数据库
 * @param jsondata
 */
function stu_jdbc(jsondata) {
    $.ajax({
        type:"post",//向服务器发出请求的方法
        url:"/Server/student", //向服务器请求的url
        data:jsondata, //注意，就算不需要发送数据给后端也要有data
        //dataType:"JSON",
        // //ajax2.0可以不用设置请求头，但是jq帮我们自动设置了，这样的话需要我们自己取消掉
        // contentType:false,
        // //取消帮我们格式化数据，是什么就是什么
        // processData:false,
        success:function (data) {
            //设置选择框里的值
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
                createStudentTable(data);
            }else {
                stu_pageTurning(6);
            }
        },
        error:function (e) {
            //在切换页面的时候有错误
            alert(e.readyState);
            alert(e.status );
            alert(e.statusText  );
            alert(e.responseText);
            alert("stu_jdbc发生错误");
        }
    });
}

/**
 * 创建表格
 * @param data
 */
function createStudentTable(data) {
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

        //从后端获得班级对应 id 的json 数组
        var clazz_names = clazz.filter((p) => {
            return p.clas_id == data.list[i].clas_id;
        });
        //从clazz_name 获得老师对应 id 的 json数组
        var teac_names = teacher.filter((p) => {
            return p.teac_id == clazz_names[0].teac_id;
        });
        var msg = "<tr onclick='checkboxTrue(this)' >"+
            "<td class='align-self-center'> <input type=\"checkbox\" stu_id='"+ data.list[i].stu_id +"' id='check"+i+"' /></td>"+
            "<th scope=\"row\">"+data.list[i].stu_id+"</th>"+
            "<td>"+data.list[i].stu_name+"</td>"+
            "<td>"+data.list[i].stu_sex+"</td>"+
            "<td>"+data.list[i].stu_age+"</td>"+
            "<td>"+data.list[i].stu_totalPeriod+"</td>"+
            "<td>"+data.list[i].stu_remainPeriod+"</td>"+
            "<td>"+teac_names[0].teac_name+"</td>"+
            "<td>"+clazz_names[0].clas_name+"</td>"+
            "</tr>";
        //添加到数组中
        pageData.push(msg);
        //或得student数据
        student.push({
            "stu_id":data.list[i].stu_id,
            "stu_name":data.list[i].stu_name,
            "stu_sex":data.list[i].stu_name,
            "stu_age":data.list[i].stu_age,
            "stu_totalPeriod":data.list[i].stu_totalPeriod,
            "stu_remainPeriod":data.list[i].stu_remainPeriod,
            "teac_id":teac_names[0].teac_id,
            "clas_id":clazz_names[0].clas_id
        });
    }
    document.getElementById("totalMsg").innerHTML="共"+totalPage+"页"+" "+totalRecord+"条记录";
    //创建换页标签
    createStudentA();
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
function createStudentA(){
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
                stu_pageTurning(2);
            }else if($(this).attr("aria-label") == "Next"){
                //下一页
                stu_pageTurning(3);
            }
        }else {
            //包含数字 指哪跳哪
            pageNum = selectPage;
            //加载所有数据的表格
            stu_pageTurning(0);
        }
    });
}


/**
 * 页面调换 {1，2，3，4,5,6}
 * 1表示首页 2表示上一页 3表示下一页 4表示尾页 5表示GO跳转到指定页面 6表示 查看按钮
 * @param btnNumber
 */
function stu_pageTurning(btnNumber) {
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
        //是查询
        stuAlter('search');
    }else {
        //不是查询 加载所有数据的表格
        stuAlter('findAll');
    }
}

/**
 * 是否为查询
 * @returns {boolean}
 * @constructor
 */
function IsSearch() {
    var searchInput = "";
    var searchSelect = 0 ;
    $("#stu_tr_Search input[type='text']").each(function(){
        if($(this).attr("id")=="goPageInp"){
            alert("goPageInp");
            return  false;
        }
        searchInput += $(this).val();
    });
    $('#stu_tr_Search select').each(function(){
        if($(this).val()!=null){
            searchSelect += parseInt($(this).val());
        }
    });
    alert(searchInput);
    if(searchInput==""&&searchSelect==0){
        //输入框和选择框都没有数据 不是查询
        return false;
    }
    //有数据 是查询
    return true;
}