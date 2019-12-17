<%--
  Created by IntelliJ IDEA.
  User: fanfan
  Date: 12/14/2019
  Time: 9:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@ page import="com.marlabs.shopping.entity.User" %>

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
</head>
<body>
<!--navbar-->

<%
    if (session.getAttribute("currentUser") == null) {
        User user = new User();
        user.setUid(-1);
        session.setAttribute("currentUser",user);
    }
%>


<div class="site-wrap">
    <div class="site-navbar bg-white py-2">
        <div class="container">
            <div class="d-flex align-items-center justify-content-between">
                <div class="logo">
                    <div class="site-logo">
                        <a href="${cp}/getAllProducts" class="js-logo-clone">ShopMax</a>
                    </div>
                </div>
                <div class="icons">
                    <!-- if not login -->
                    <c:if test="${empty currentUser || currentUser.uid == -1}">
                        <a href="${cp}/register" methods="post">Register&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        <a href="${cp}/login" methods="post">Login&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        <a href="${cp}/getAllShoppingCarts" class="icons-btn d-inline-block bag">
                            <span class="icon-shopping-bag"></span>
                        </a>
                    </c:if>
                    <!-- if login -->
                    <c:if test="${not empty currentUser && currentUser.uid != -1}">
                        <!--WishList-->
                        <a href="${cp}/getAllWishLists" class="icons-btn d-inline-block"><span class="icon-heart-o"></span></a>
                        <!--ShoppingCart-->
                        <a href="${cp}/getAllShoppingCarts" class="icons-btn d-inline-block bag">
                            <span class="icon-shopping-bag"></span>
                        </a>
                        <a href="">&nbsp;&nbsp;${currentUser.nickname}&nbsp;&nbsp;</a>
                        <a href="${cp}/doLogout">&nbsp;&nbsp;Logout&nbsp;&nbsp;</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
