<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta charset="utf-8">
    <title>login</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 核心 CSS 文件 -->
    <!--<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">-->
    <link rel="stylesheet" type="text/css" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>

<body background="images/bg.jpg"
      style=" background-repeat: no-repeat ;

             background-size: 100% 100%;
             background-attachment: fixed;"
>
<div class="container">
    <div class="row row-centered">
        <div class="well col-md-6 col-centered">
            <h2 class="text-center" id="login_show"> 登 录 </h2>
            <!--action="/servlet" method="post"-->
            <form  role="form" id="login_from">
                <div class="form-row">
                    <div class="input-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend1"><i class="fa fa-user" style="font-size:24px"></i></span>
                            </div>
                            <input type="text" class="form-control" id="userid" aria-describedby="inputGroupPrepend1" name="phoneNumber" placeholder="请输入电话" required>
                            <div class="invalid-feedback" id="phoneMsg">
                                请输入账号！
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="input-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend2"><i class="fa fa-lock" style="font-size:24px" ></i></span>
                            </div>
                            <input type="password" class="form-control" id="password" aria-describedby="inputGroupPrepend2" name="passWord" placeholder="请输入密码" required>
                            <div class="invalid-feedback" id="passwordMsg">
                                请输入密码！
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
                <button type="button" onclick="remember()" class="btn btn-success btn-block">登 录</button>
            </form>
        </div>
    </div>
</div>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="js/jquery-3.4.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="js/bootstrap.min.js"></script>
<script src="js/Login.js"></script>
</body>
</html>