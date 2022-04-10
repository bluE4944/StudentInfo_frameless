<%--
  Created by IntelliJ IDEA.
  User: 11940
  Date: 2020/3/31
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <% String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <meta charset="utf-8">
    <title>StudentInfo</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" type="text/css" href="<%= basePath %>css/bootstrap.min.css">
    <!-- 图标CSS文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="<%= basePath %>css/prime.css">
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div id="page_left" class="width240">
            <div class="col left_topCss"><span id="left_top"></span>  <i id="onNav" class="fa fa-close" style="font-size:36px; float: right"></i></div>
            <ul id="myTab" class="nav flex-column">
                <li  class="active1 nav-item">
                    <a class="nav-link active a_active" id="li_welcome" onclick="toPage('welcome')" href="#"><i class="fa fa-cube" style="font-size:24px"></i><span>&nbsp;&nbsp;&nbsp;Welcome</span></a>
                </li>
                <li  class=" nav-item">
                    <a class="nav-link active" id="li_staff" onclick="toPage('staff')" href="#"><i class="fa fa-users" style="font-size:24px"></i><span>&nbsp;&nbsp;&nbsp;人员列表</span></a>
                </li>
                <li  class=" nav-item">
                    <a class="nav-link active" id="li_student" onclick="toPage('student')" href="#"><i class="fa fa-mortar-board" style="font-size:24px"></i><span>&nbsp;&nbsp;学生列表</span></a>
                </li>
                <li  class=" nav-item">
                    <a class="nav-link active" id="li_teacher" onclick="toPage('teacher')" href="#"><i class="fa fa-user-circle-o" style="font-size:24px"></i><span>&nbsp;&nbsp;&nbsp;老师列表</span></a>
                </li>
                <li  class=" nav-item">
                    <a class="nav-link active" id="li_class" onclick="toPage('class')" href="#"><i class="fa fa-list-alt" style="font-size:24px"></i><span>&nbsp;&nbsp;&nbsp;班级列表</span></a>
                </li>
                <li  class=" nav-item">
                    <a class="nav-link active" id="li_curriculum" onclick="toPage('course')" href="#"><i class="fa fa-calendar" style="font-size:24px"></i><span>&nbsp;&nbsp;&nbsp;课程列表</span></a>
                </li>
                <li  class=" nav-item">
                    <a class="nav-link active" id="li_salary" onclick="toPage('salary')" href="#"><i class="fa fa-bar-chart" style="font-size:24px"></i><span>&nbsp;&nbsp;统计工资</span></a>
                </li>
                <li  class=" nav-item">
                    <a class="nav-link active" id="li_setting" onclick="toPage('setting')" href="#"><i class="fa fa-sliders" style="font-size:24px"></i><span>&nbsp;&nbsp;&nbsp;所有设置</span></a>
                </li>

            </ul>
        </div>
        <div ></div>
        <div id="page_right" class="col bg-light">
            <div id="right_top" class="col-12">
                <div class="row justify-content-between">
                    <div id="rTop_left">
                        <span class="align-middle" id="nav_show"><i id="fa_bars" class="fa fa-bars" style="font-size:36px"></i></span>
                        <span class="align-middle" id="systemName"></span>
                    </div>
                    <!--退出登录，清除cookie信息-->

                    <div id="login_out">
                        <!--登陆名称显示-->
                        <span id="login_welcome">  </span>
                        <a style="color: white; margin-left: 15px" href="/Server" onclick="loginOut()"><i class="fa fa-sign-out"></i> <span>退出</span></a>
                    </div>
                </div>
            </div>
            <!--主要内容显示页面-->
            <div id="right_down" class="col">

            </div>
        </div>
    </div>
</div>


<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="<%= basePath %>js/jquery-3.4.1.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="<%= basePath %>js/bootstrap.min.js"></script>
<script src="<%= basePath %>js/laydate/laydate.js"></script>
<script src="<%= basePath %>js/prime.js"></script>
<script src="<%= basePath %>js/page_staff.js"></script>
<script src="<%= basePath %>js/page_setting.js"></script>
<script src="<%= basePath %>js/page_student.js"></script>
<script src="<%= basePath %>js/page_teacher.js"></script>
<script src="<%= basePath %>js/page_class.js"></script>
<script src="<%= basePath %>js/page_course.js"></script>
</body>
</html>

