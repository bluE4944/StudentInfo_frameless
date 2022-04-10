<%--
  Created by IntelliJ IDEA.
  User: 11940
  Date: 2020/4/4
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%--
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
    <title>setting</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
<div class="container">

    <div class="row">
        <h1>关于个人</h1>
    </div>
    <div class="accordion" id="accordionExample2">
        <div class="card">
            <div class="card-header" id="headingOne2">
                <h2 class="mb-0">
                    <button class="btn btn-outline-info btn-block btn-lg text-left" type="button" data-toggle="collapse" data-target="#collapseOne2" aria-expanded="true" aria-controls="collapseOne2">
                        修改个人密码
                    </button>
                </h2>
            </div>

            <div id="collapseOne2" class="collapse" aria-labelledby="headingOne2" data-parent="#accordionExample2">
                <div class="card-body">
                    <form>
                        <div class="form-group row">
                            <label for="oldPassword" class="col-sm-2 col-form-label">原密码</label>
                            <div class="col-sm-10">
                                <input type="password"  class="form-control" id="oldPassword">
                                <div class="invalid-feedback">
                                    请输入原密码！
                                </div>
                            </div>

                        </div>
                        <div class="form-group row">
                            <label for="newPassword" class="col-sm-2 col-form-label">新密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="newPassword">
                                <div class="invalid-feedback">
                                    请输入新密码！
                                </div>
                            </div>

                        </div>
                        <button type="button" class="btn btn-info btn-block" onclick="setName('setPassword')">修改</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTwo2">
                <h2 class="mb-0">
                    <button class="btn btn-outline-info btn-block text-left btn-lg collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo2" aria-expanded="false" aria-controls="collapseTwo2">
                        也妹想好
                    </button>
                </h2>
            </div>
            <div id="collapseTwo2" class="collapse" aria-labelledby="headingTwo2" data-parent="#accordionExample2">
                <div class="card-body">
                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingThree2">
                <h2 class="mb-0">
                    <button class="btn btn-outline-info btn-block btn-lg text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseThree2" aria-expanded="false" aria-controls="collapseThree2">
                        还是妹想好
                    </button>
                </h2>
            </div>
            <div id="collapseThree2" class="collapse" aria-labelledby="headingThree2" data-parent="#accordionExample2">
                <div class="card-body">
                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <h1>关于系统</h1>
    </div>
    <div class="accordion" id="accordionExample1">
        <div class="card">
            <div class="card-header" id="headingOne">
                <h2 class="mb-0" onclick="getAllName()">
                    <button class="btn btn-outline-dark btn-block btn-lg text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne" >
                        修改系统名称
                    </button>
                </h2>
            </div>

            <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample1">
                <div class="card-body">
                    <form>
                        <div class="form-group row">
                            <label for="oldSystemName" class="col-sm-2 col-form-label">原名称</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="oldSystemName" value="默认">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputSystemName" class="col-sm-2 col-form-label">新名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputSystemName">
                                <div class="invalid-feedback">
                                    请输入新名称！
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-dark btn-block" onclick="setName('setSystemName')">修改</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h2 class="mb-0" onclick="getAllName()">
                    <button class="btn btn-outline-dark btn-block text-left btn-lg collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        更改登陆页面显示
                    </button>
                </h2>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample1">
                <div class="card-body">
                    <form>
                        <div class="form-group row">
                            <label for="loginName" class="col-sm-2 col-form-label">原显示</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="loginName" value="默认">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputLoginName" class="col-sm-2 col-form-label">新显示</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputLoginName">
                                <div class="invalid-feedback">
                                    请输入新显示！
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-dark btn-block" onclick="setName('setLoginName')">修改</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingThree">
                <h2 class="mb-0" onclick="getAllName()">
                    <button class="btn btn-outline-dark btn-block text-left btn-lg collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        更改导航栏显示
                    </button>
                </h2>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample1">
                <div class="card-body">
                    <form>
                        <div class="form-group row">
                            <label for="loginName" class="col-sm-2 col-form-label">原显示</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="navName" value="默认">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputNavName" class="col-sm-2 col-form-label">新显示</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="inputNavName">
                                <div class="invalid-feedback">
                                    请输入新显示！
                                </div>
                            </div>
                        </div>
                        <button type="button" class="btn btn-dark btn-block" onclick="setName('setNavName')">修改</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingFour">
                <h2 class="mb-0">
                    <button class="btn btn-outline-dark btn-block text-left btn-lg collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                        使用手册
                    </button>
                </h2>
            </div>
            <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionExample1">
                <div class="card-body">
                    <p class="h4">人员管理</p>
                    <p> 可以添加用户，但看不到密码也不能设置别人的密码，每个新添加的用户密码默认123456，可在每个人的个人设置里更改（只有自己能更改自己的密码）</p>
                    <p class="h4">学生管理</p>
                    <p> 查看按钮可以查看数据库里的数据，点击查看按钮后可以直接更改某个数据，更改后一定要选中更改的那行，再点击保存按钮才能保存更改。如果点击了查看按钮后想要添加记录的话，需要点击清空按钮，根据需要添加需要保存的记录条数，再保存才是添加数据到数据库中</p>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="<%= basePath %>js/bootstrap.min.js"></script>
<script src="<%= basePath %>js/page_setting.js"></script>
</body>
</html>