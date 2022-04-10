<%--
  Created by IntelliJ IDEA.
  User: 11940
  Date: 2020/4/12
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <% String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <title>teacher</title>
</head>
<body>
<div class="container-fluid text-nowrap text-center">
    <div class="row justify-content-between purePadding_15 basicBox shadow" >
        <div id="buttons_latter">
            <button class="btn btn-outline-success"  data-toggle="modal" data-target="#teac_save"><i class="fa fa-plus-circle" style="font-size:24px"></i> <span> 添加 </span> </button>
            <!-- Modal -->
            <div class="modal fade" id="teac_save" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalCenterTitle"><i class="fa fa-drivers-license" style="font-size:24px"></i> 添加教师</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group row">
                                    <label for="inputTeac_name" class="col-sm-1 col-form-label" ><i class="fa fa-terminal" style="font-size:24px"></i> </label>
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control" id="inputTeac_name" placeholder=" 姓名">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputTeac_phoneNumber" class="col-sm-1 col-form-label"  ><i class="fa fa-phone-square" style="font-size:24px"></i></label>
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control" id="inputTeac_phoneNumber" placeholder=" 电话">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="inputTeac_address" class="col-sm-1 col-form-label" ><i class="fa fa-address-book" style="font-size:24px"></i> </label>
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control" id="inputTeac_address" placeholder=" 地址">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-reply" style="font-size:24px"></i> Close</button>
                            <button type="button" class="btn btn-primary" onclick="teacAlter('save')"><i class="fa fa-save" style="font-size:24px"></i> Save</button>
                        </div>
                    </div>
                </div>
            </div>

            <button class="btn btn-outline-danger"  onclick="teacAlter('delete')"><i class="fa fa-trash" style="font-size:24px"></i> <span> 删除 </span> </button>
            <button class="btn btn-outline-dark" onclick="teacAlter('findAll')"><i class="fa fa-eercast" style="font-size:24px"></i> <span> 刷新 </span> </button>
        </div>
        <div class="row align-items-center">
            <h3> 教师列表 </h3>
        </div>
        <div class="row align-items-center purePadding_15">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon2"><i class="fa fa-search" style="font-size:24px"></i></span>
                </div>
                <select id="selectTeacher" onchange="clasAlter('withTeac_id')"  class="form-control " >
                    <option value="0" selected>teacher</option>
                </select>
            </div>
        </div>
    </div>


    <div class="row table-responsive-xl basicBox shadow">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="align-self-center"><input type="checkbox" id='all' onclick='checkAll()'/></th>
                <th scope="col">id</th>
                <th scope="col">姓名</th>
                <th scope="col">电话</th>
                <th scope="col">地址</th>
            </tr>
            </thead>
            <tbody id="table">

            </tbody>
        </table>
    </div>
    <div class="row justify-content-center displayNone basicBox align-items-center shadow ubPadding_15" id="btnTurning">
        <!--总页数--><!--总记录数-->
        <div class="padding_15 margin" id="totalMsg">
        </div>
        <div>
            <button type="button" class="btn btn-outline-dark" onclick="teac_pageTurning(1)">首页</button>
        </div>
        <div id="aWidth" >
            <nav aria-label="Page navigation example">
                <ul class="pagination noMargin" id="paging">

                </ul>
            </nav>
        </div>
        <div >
            <button type="button" class="btn btn-outline-dark" onclick="teac_pageTurning(4)">尾页</button>
        </div>
        <div>
            <div class="row displayNone padding_15" id="goPageDiv">
                <div class="Width_60">
                    <input type="number" class="form-control" id="goPageInp">
                </div>页
                <button class="btn btn-outline-dark" onclick="teac_pageTurning(5)">GO</button>
            </div>
        </div>
    </div>

</div>

<script src="<%= basePath %>js/page_teacher.js"></script>
</body>
</html>
