﻿<%--
  Created by IntelliJ IDEA.
  User: 11940
  Date: 2020/4/2
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <% String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta charset="utf-8">
    <title>staff</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<div class="container-fluid text-nowrap text-center">
    <div class="row basicBox shadow rounded-lg align-items-center" id="userSqlOperation ">
        <div class="col-md-2">
            <span>id:</span>
            <input type="text" class="form-control" id="input_uid"/>
        </div>
        <div class="col-md-2">
            <span>姓名:</span>
            <input type="text" class="form-control" id="input_uname"/>
        </div>
        <div class="col-md-2">
            <span>电话:</span>
            <input type="text" class="form-control" id="input_uphoneNumber" />
        </div>
        <div class="col-md-1" id="buttons_front">
            <input type="checkbox" id="exampleCheck1"/>
            <label class="form-check-label" for="exampleCheck1">管理员</label>
        </div>
        <div class="col-md-4" id="buttons_latter">
            <button class="btn btn-outline-success" onclick="userAlter('save')"><i class="fa fa-save" style="font-size:24px"></i>  <span> 保存 </span> </button>
            <button class="btn btn-outline-primary" onclick="userAlter('select')"><i class="fa fa-search" style="font-size:24px"></i> <span> 查找 </span> </button>
            <button class="btn btn-outline-info" onclick="userAlter('update')"><i class="fa fa-edit" style="font-size:24px"></i> <span> 修改 </span> </button>
            <button class="btn btn-outline-danger" onclick="userAlter('delete')"><i class="fa fa-trash" style="font-size:24px"></i> <span> 删除 </span> </button>
            <button class="btn btn-outline-dark" onclick="pageTurning(6)" ><i class="fa fa-eercast" style="font-size:24px"></i> <span> 刷新 </span></button>
        </div>
    </div>
    <div class="row table-responsive-xl rounded-lg basicBox shadow">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="align-self-center"><input type="checkbox"  id='all' onclick='checkAll()'/></th>
                <th scope="col">id</th>
                <th scope="col">姓名</th>
                <th scope="col">电话</th>
                <th scope="col">admin</th>
                <th scope="col">操作时间</th>
            </tr>
            </thead>
            <tbody id="table">

            </tbody>

        </table>
    </div>
    <div class="row justify-content-center displayNone  basicBox align-items-center shadow ubPadding_15" id="btnTurning">
        <!--总页数--><!--总记录数-->
        <div class="padding_15 margin" id="totalMsg">
        </div>
        <div>
            <button type="button" class="btn btn-outline-dark" onclick="pageTurning(1)">首页</button>
        </div>
        <div id="aWidth" >
            <nav aria-label="Page navigation example">
                <ul class="pagination noMargin" id="paging">

                </ul>
            </nav>
        </div>
        <div >
            <button type="button" class="btn btn-outline-dark" onclick="pageTurning(4)">尾页</button>
        </div>
        <div>
            <div class="row displayNone padding_15" id="goPageDiv">
                <div class="Width_60">
                    <input type="number" class="form-control" id="goPageInp">
                </div>页
                <button class="btn btn-outline-dark" onclick="pageTurning(5)">GO</button>
            </div>
        </div>
    </div>
</div>

<script src="<%= basePath %>js/page_staff.js"></script>

</body>
</html>