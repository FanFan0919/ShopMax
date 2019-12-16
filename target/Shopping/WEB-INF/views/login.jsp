<%--
  Created by IntelliJ IDEA.
  User: fanfan
  Date: 12/14/2019
  Time: 10:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container-fluid" style="padding-top: 80px;padding-bottom: 80px">

    <h1 class="title center">Login</h1>
    <br/>
    <div class="col-sm-offset-2 col-md-offset-2" style="margin-left:450px">
        <!-- form input -->
        <div  class="form-horizontal" style="display: block;">
            <div class="form-group">
                <label for="inputUsername" class="col-sm-2 col-md-2 control-label">Username</label>
                <div class="col-sm-6 col-md-6">
                    <input type="text" class="form-control" id="inputUsername" placeholder="root"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 col-md-2 control-label">password</label>
                <div class="col-sm-6 col-md-6">
                    <input type="password" class="form-control" id="inputPassword" placeholder="123456" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="startLogin()">Login</button>
                </div>
            </div>
        </div>
        <br/>
    </div>
</div>

<!--footer-->
<jsp:include page="include/foot.jsp"/>

<script type="text/javascript">
    function startLogin() {
        var loading = layer.load(0);
        var user = {};
        var loginResult = "";
        user.username = document.getElementById("inputUsername").value;
        user.password = document.getElementById("inputPassword").value;
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/doLogin',
            data : user,
            dataType : 'json',
            success : function(result) {
                loginResult = result.result;
                layer.close(loading);
            },
            error : function(result) {
                layer.alert('Fail to send the login request');
            }
        });

        if(loginResult == 'success'){
            layer.msg('success',{icon:1});
            window.location.href = "${cp}/getAllProducts";
        }
        else if(loginResult == 'wrongUser'){
            layer.msg('wrong username',{icon:2});
        }
        else if(loginResult == 'wrongPassword'){
            layer.msg('wrong password',{icon:2});
        }
        else if(loginResult == 'fail'){
            layer.msg('server error',{icon:2});
        }

    }
</script>

</body>
</html>
