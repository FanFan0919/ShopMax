<%--
  Created by IntelliJ IDEA.
  User: fanfan
  Date: 12/14/2019
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>ShopMax</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
    <link rel="stylesheet" href="${cp}/fonts/icomoon/style.css">

    <link rel="stylesheet" href="${cp}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${cp}/css/magnific-popup.css">
    <link rel="stylesheet" href="${cp}/css/jquery-ui.css">
    <link rel="stylesheet" href="${cp}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${cp}/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="${cp}/css/aos.css">
    <link rel="stylesheet" href="${cp}/css/style.css">

    <script src="${cp}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="${cp}/js/jquery-ui.js" type="text/javascript"></script>
    <script src="${cp}/js/popper.min.js" type="text/javascript"></script>
    <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${cp}/js/owl.carousel.min.js" type="text/javascript"></script>
    <script src="${cp}/js/jquery.magnific-popup.min.js" type="text/javascript"></script>
    <script src="${cp}/js/aos.js" type="text/javascript"></script>
    <script src="${cp}/js/layer.js" type="text/javascript"></script>
    <script src="${cp}/js/main.js" type="text/javascript"></script>
</head>
<body>
<!--navbar-->
<jsp:include page="include/header.jsp"/>

<!-- body -->
<div class="container-fluid">
    <h1 class="title center">Register</h1>
    <br/>
    <div class="col-sm-offset-2 col-md-offest-2" style="margin-left:450px">
        <!-- form input -->
        <div  class="form-horizontal">
            <div class="form-group">
                <label for="inputUserName" class="col-sm-2 col-md-2 control-label">UserName</label>
                <div class="col-sm-6 col-md-6">
                    <input type="text" class="form-control" id="inputUserName" placeholder="root"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 col-md-2 control-label">Password</label>
                <div class="col-sm-6 col-md-6">
                    <input type="password" class="form-control" id="inputPassword" placeholder="123456" />
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="col-sm-2 col-md-2 control-label">Email</label>
                <div class="col-sm-6 col-md-6">
                    <input type="email" class="form-control" id="inputEmail" placeholder="test@marlabs.com"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputNickname" class="col-sm-2 col-md-2 control-label">Nickname</label>
                <div class="col-sm-6 col-md-6">
                    <input type="text" class="form-control" id="inputNickname" placeholder="Vincent" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="startRegister()">Register</button>
                </div>
            </div>
        </div>
        <br/>
    </div>
</div>

<!--footer-->
<jsp:include page="include/foot.jsp"/>

<script type="text/javascript">
    function startRegister() {
        var loading = layer.load(0);
        var user = {};
        user.userName = document.getElementById("inputUserName").value;
        user.password = document.getElementById("inputPassword").value;
        user.email = document.getElementById("inputEmail").value;
        user.nickname = document.getElementById("inputNickname").value;

        if(user.userName == ''){
            layer.msg('Username can not be empty!',{icon:2});
            return;
        }
        else if(user.password == ''){
            layer.msg('Password can not be empty!',{icon:2});
            return;
        }
        else if(user.nickname == ''){
            layer.msg('Nickname can not be empty!',{icon:2});
            return;
        }
        else if(user.email == ''){
            layer.msg('Email can not be empty!',{icon:2});
            return;
        }
        var registerResult = null;
        $.ajax({
            async : false, //disable async
            type : 'POST',
            url : '${cp}/doRegister',
            data : user,
            dataType : 'json',
            success : function(result) {
                registerResult = result.result;
            },
            error : function(result) {
                layer.alert('Fail to send the register request');
            }
        });
        if(registerResult == 'success'){
            layer.close(loading);
            layer.msg('Success!',{icon:1});
            window.location.href="${cp}/login";
        }
        else if(registerResult == 'nameExist'){
            layer.close(loading);
            layer.msg('This username is already registered, please change a username',{icon:2});
        }
        else if(registerResult == 'emailExist'){
            layer.close(loading);
            layer.msg('This email is already registered, please change an email',{icon:2});
        }
        else if(registerResult == 'fail'){
            layer.close(loading);
            layer.msg('server error',{icon:2});
        }
    }
</script>
</body>
</html>
