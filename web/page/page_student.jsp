<%--
  Created by IntelliJ IDEA.
  User: 11940
  Date: 2020/4/4
  Time: 16:13
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
    <title>student</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<div class="container-fluid text-nowrap text-center">

    <div class="row justify-content-between purePadding_15 basicBox shadow">
        <div id="buttons_latter">
            <button class="btn btn-success"  data-toggle="modal" data-target="#stu_save"><i class="fa fa-plus-circle" style="font-size:24px"></i>  <span> 添加 </span></button>
            <!-- Modal -->
            <div class="modal fade bd-example-modal-lg" id="stu_save" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalCenterTitle"><i class="fa fa-id-badge" style="font-size:24px"></i> 添加学生</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group row">
                                    <label for="inputStu_name" class="col-sm-1 col-form-label" ><i class="fa fa-terminal" style="font-size:24px"></i> </label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="inputStu_name" placeholder=" 姓名">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="selectStu_sex" class="col-sm-1 col-form-label"  ><i class="fa fa-venus-mars" style="font-size:24px"></i></label>
                                    <div class="col-sm-4">
                                        <select id="selectStu_sex" class="form-control">
                                            <option value="0" selected>sex</option>
                                            <option value="1">男</option>
                                            <option value="2">女</option>
                                        </select>
                                    </div>
                                    <div class="col-1"></div>
                                    <label for="inputStu_age" class="col-sm-1 col-form-label" ><i class="fa fa-odnoklassniki" style="font-size:24px"></i> </label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" id="inputStu_age" placeholder=" 年龄">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="selectTeacherSave" class="col-sm-1 col-form-label"  ><i class="fa fa-user-circle-o" style="font-size:24px"></i></label>
                                    <div class="col-sm-4">
                                        <select id="selectTeacherSave" onclick="selectTeacher()" onchange="selectClass()" class="form-control">
                                            <option value="0" selected>teacher</option>
                                        </select>
                                    </div>
                                    <div class="col-1"></div>
                                    <label for="selectClassSave" class="col-sm-1 col-form-label"  ><i class="fa fa-calendar" style="font-size:24px"></i></label>
                                    <div class="col-sm-4">
                                        <select id="selectClassSave" class="form-control">
                                            <option value="0" selected>class</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-reply" style="font-size:24px"></i> Close</button>
                            <button type="button" class="btn btn-primary" onclick="stuAlter('save')"><i class="fa fa-save" style="font-size:24px"></i> Save</button>
                        </div>
                    </div>
                </div>
            </div>

            <button id="stu_edit" class="btn btn-info" onclick="stuAlter('edit')"><i class="fa fa-edit" style="font-size:24px"></i> <span> 编辑 </span> </button>
            <button class="btn btn-danger" onclick="stuAlter('delete')"><i class="fa fa-trash"  style="font-size:24px"></i> <span> 删除 </span> </button>
            <button class="btn btn-dark" onclick="stuAlter('findAll')"><i class="fa fa-eercast" style="font-size:24px"></i> <span> 刷新 </span></button>
        </div>
    </div>

    <div class="row table-responsive-xl rounded-lg basicBox shadow ">
        <table class="table table-bordered" id="stu_table">
            <thead>
            <tr id="stu_th" onclick="showSearch()">
                <th scope="col" class="align-self-center"><input type="checkbox" id="all" onclick="checkAll()"/></th>
                <th scope="col">id</th>
                <th scope="col-md-2">姓名</th>
                <th scope="col-md-2">性别</th>
                <th scope="col">年龄</th>
                <th scope="col">剩余课时</th>
                <th scope="col">总课时</th>
                <th scope="col">老师</th>
                <th scope="col">班级</th>
            </tr>
            <tr class="displayNone" id="stu_tr_Search">
                <td>
                    <span class="border border-primary rounded-circle">
                        <i class="fa fa-search" style="font-size:24px"></i>
                    </span>
                </td>
                <td colspan="2">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="name" id="search_stu_sex" onkeydown="searchKeydown(this)" />
                    </div>
                </td>
                <td>
                    <div class="form-row">
                        <select id="SearchSex" class="form-control">
                            <option value="0" selected>sex</option>
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrependAge">
                                    <select id="SearchAge" >
                                        <option value="0" selected> = </option>
                                        <option value="1"> < </option>
                                        <option value="2"> > </option>
                                        <option value="3"> - </option>
                                    </select>
                                </span>
                        </div>
                        <input type="text" class="form-control" id="inputSearchAge" placeholder="Age" aria-describedby="inputGroupPrependAge" onkeydown="searchKeydown(this)" />
                    </div>
                </td>
                <td>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend2">
                                    <select id="SearchRemainPeriod" >
                                        <option value="0" selected> = </option>
                                        <option value="1"> < </option>
                                        <option value="2"> > </option>
                                        <option value="3"> - </option>
                                    </select>
                                </span>
                        </div>
                        <input type="text" class="form-control" id="inputSearchRemainPeriod" placeholder="RemainPeriod" aria-describedby="inputGroupPrepend2" onkeydown="searchKeydown(this)"/>
                    </div>
                </td>
                <td>
                    <div class="input-group">
                        <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend3">
                                    <select id="SearchTotalPeriod" >
                                        <option value="0" selected> = </option>
                                        <option value="1"> < </option>
                                        <option value="2"> > </option>
                                        <option value="3"> - </option>
                                    </select>
                                </span>
                        </div>
                        <input type="text" class="form-control" id="inputSearchTotalPeriod" placeholder="TotalPeriod" aria-describedby="inputGroupPrepend3" onkeydown="searchKeydown(this)" />
                    </div>
                </td>
                <td>
                    <div class="form-row">
                        <select id="selectTeacher" onchange="selectClass()" class="form-control">
                            <option value="0" selected>teacher</option>
                        </select>
                    </div>
                </td>
                <td>
                    <div class="form-row">
                        <select id="selectClass" class="form-control">
                            <option value="0" selected>class</option>
                        </select>
                    </div>
                </td>
            </tr>
            </thead>
            <tbody id="table">
            </tbody>
        </table>
    </div>
    <!--换页-->
    <div class="row justify-content-center displayNone  basicBox align-items-center shadow ubPadding_15" id="btnTurning">
        <!--总页数--><!--总记录数-->
        <div class="padding_15 margin" id="totalMsg">
        </div>
        <div>
            <button type="button" class="btn btn-outline-dark" onclick="stu_pageTurning(1)">首页</button>
        </div>
        <div id="aWidth" >
            <nav aria-label="Page navigation example">
                <ul class="pagination noMargin" id="paging">

                </ul>
            </nav>
        </div>
        <div >
            <button type="button" class="btn btn-outline-dark" onclick="stu_pageTurning(4)">尾页</button>
        </div>
        <div>
            <div class="row displayNone padding_15" id="goPageDiv">
                <div class="Width_60">
                    <input type="number" class="form-control" id="goPageInp">
                </div>页
                <button class="btn btn-outline-dark" onclick="stu_pageTurning(5)">GO</button>
            </div>
        </div>
    </div>


</div>
<script src="<%= basePath %>js/page_student.js"></script>
</body>
</html>
