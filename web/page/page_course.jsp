<%--
  Created by IntelliJ IDEA.
  User: 11940
  Date: 2020/4/9
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>course</title>
</head>
<body>
<div class="container-fluid text-nowrap text-center ">
    <div class="row justify-content-between purePadding_15 basicBox rounded-lg shadow" >
        <div id="buttons_latter">
            <button class="btn btn-outline-success" data-toggle="modal" data-target="#teac_save"><i class="fa fa-plus-circle" style="font-size:24px"></i> <span> 添加 </span> </button>
            <!-- Modal -->
            <div class="modal fade" id="teac_save" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalCenterTitle"><i class="fa fa-list-alt" style="font-size:24px"></i> 添加课程</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group row">
                                    <label for="inputCour_name" class="col-sm-1 col-form-label" ><i class="fa fa-terminal" style="font-size:24px"></i> </label>
                                    <div class="col-sm-11">
                                        <input type="text" class="form-control" id="inputCour_name" placeholder=" 课程名称" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="selectSubjectSave" class="col-sm-1 col-form-label"  ><i class="fa fa-leaf" style="font-size:24px"></i></label>
                                    <div class="col-sm-11">
                                        <select id="selectSubjectSave" onchange=""  class="form-control " >
                                            <option value="0" selected>object</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="cour_introduction" class="col-sm-1 col-form-label"  ><i class="fa fa-leanpub" style="font-size:24px"></i></label>
                                    <div class="col-sm-11">
                                        <textarea class="form-control" id="cour_introduction" rows="3" placeholder=" 课程简介"></textarea>
                                    </div>
                                </div>

                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-reply" style="font-size:24px"></i> Close</button>
                            <button type="button" class="btn btn-primary" onclick="courAlter('save')"><i class="fa fa-save" style="font-size:24px"></i> Save</button>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-outline-danger"  onclick="courAlter('delete')"><i class="fa fa-trash" style="font-size:24px"></i> <span> 删除 </span> </button>
            <button class="btn btn-outline-dark" onclick="courAlter('findAll')"><i class="fa fa-eercast" style="font-size:24px"></i> <span> 刷新 </span> </button>
        </div>
        <div class="row align-items-center">
            <h3> 课程列表 </h3>
        </div>
        <div class="row align-items-center purePadding_15">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon2"><i class="fa fa-search" style="font-size:24px"></i></span>
                </div>
                <select id="selectSubject" onchange="courAlter('Search')"  class="form-control " >
                    <option value="0" selected>subject</option>
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
                <th scope="col">课程名称</th>
                <th scope="col">课程主题</th>
                <th scope="col">课程介绍</th>
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
            <button type="button" class="btn btn-outline-dark" onclick="cour_pageTurning(1)">首页</button>
        </div>
        <div id="aWidth" >
            <nav aria-label="Page navigation example">
                <ul class="pagination noMargin" id="paging">

                </ul>
            </nav>
        </div>
        <div >
            <button type="button" class="btn btn-outline-dark" onclick="cour_pageTurning(4)">尾页</button>
        </div>
        <div>
            <div class="row displayNone padding_15" id="goPageDiv">
                <div class="Width_60">
                    <input type="number" class="form-control" id="goPageInp">
                </div>页
                <button class="btn btn-outline-dark" onclick="cour_pageTurning(5)">GO</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
