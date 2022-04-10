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
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <title>class</title>
</head>
<body>
<div class="container-fluid text-nowrap text-center ">
    <div class="row justify-content-between purePadding_15 basicBox rounded-lg shadow" >
        <div id="buttons_latter">
            <button class="btn btn-outline-success" onclick="initLaydate()" data-toggle="modal" data-target="#teac_save"><i class="fa fa-plus-circle" style="font-size:24px"></i> <span> 添加 </span> </button>
            <!-- Modal -->
            <div class="modal fade" id="teac_save" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalCenterTitle"><i class="fa fa-list-alt" style="font-size:24px"></i> 添加班级</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group row">
                                    <label for="inputClass_name" class="col-sm-1 col-form-label" ><i class="fa fa-terminal" style="font-size:24px"></i> </label>
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control" id="inputClass_name" placeholder=" 班级名称" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="selectTeacherSave" class="col-sm-1 col-form-label"  ><i class="fa fa-user-circle-o" style="font-size:24px"></i></label>
                                    <div class="col-sm-11">
                                        <select id="selectTeacherSave"  class="form-control" />
                                            <option value="0" selected>teacher</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="class_week" class="col-sm-1 col-form-label"><i class="fa fa-table" style="font-size:24px"></i></label>
                                    <div class="col-sm-11">
                                        <select id="class_week" class="form-control">
                                            <option value="0" selected> 课程时间</option>
                                            <option value="1" > 周一</option>
                                            <option value="2" > 周二</option>
                                            <option value="3" > 周三</option>
                                            <option value="4" > 周四</option>
                                            <option value="5" > 周五</option>
                                            <option value="6" > 周六</option>
                                            <option value="7" > 周日</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="class_time" class="col-sm-1 col-form-label" ><i class="fa fa-terminal" style="font-size:24px"></i> </label>
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control" id="class_time" placeholder=" 班级时间" />
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-reply" style="font-size:24px"></i> Close</button>
                            <button type="button" class="btn btn-primary" onclick="clasAlter('save')"><i class="fa fa-save" style="font-size:24px"></i> Save</button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-outline-danger"  onclick="clasAlter('delete')"><i class="fa fa-trash" style="font-size:24px"></i> <span> 删除 </span> </button>
            <button class="btn btn-outline-dark" onclick="clasAlter('findAll')"><i class="fa fa-eercast" style="font-size:24px"></i> <span> 刷新 </span> </button>
        </div>
        <div class="row align-items-center">
            <h3> 班级列表 </h3>
        </div>

        <div class="row align-items-center purePadding_15">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon2"><i class="fa fa-search" style="font-size:24px"></i></span>
                </div>
                <select id="selectTeacher" onchange="clasAlter('Search')"  class="form-control " >
                    <option value="0" selected>teacher</option>
                </select>
            </div>
        </div>
    </div>

    <div class="row table-responsive-xl rounded-lg basicBox shadow">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="align-self-center"><input type="checkbox" id='all' onclick='checkAll()'/></th>
                <th scope="col">id</th>
                <th scope="col">班级名称</th>
                <th scope="col">老师</th>
                <th scope="col">上课时间</th>
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
            <button type="button" class="btn btn-outline-dark" onclick="clas_pageTurning(1)">首页</button>
        </div>
        <div id="aWidth" >
            <nav aria-label="Page navigation example">
                <ul class="pagination noMargin" id="paging">

                </ul>
            </nav>
        </div>
        <div >
            <button type="button" class="btn btn-outline-dark" onclick="clas_pageTurning(4)">尾页</button>
        </div>
        <div>
            <div class="row displayNone padding_15" id="goPageDiv">
                <div class="Width_60">
                    <input type="number" class="form-control" id="goPageInp">
                </div>页
                <button class="btn btn-outline-dark" onclick="clas_pageTurning(5)">GO</button>
            </div>
        </div>
    </div>

</div>
</body>
</html>
