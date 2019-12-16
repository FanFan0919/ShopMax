<%--
  Created by IntelliJ IDEA.
  User: fanfan
  Date: 12/14/2019
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<!--body-->
<div class="site-wrap">
<div class="site-section">
    <div class="container">
        <div class="row">
            <div class="title-section mb-5 col-12">
                <h2 class="text-uppercase">Popular Products</h2>
            </div>
        </div>
        <div class="row">
            <c:forEach items="${productList}" var="product">
                <div class="col-lg-4 col-md-6 item-entry mb-4">
                    <a href="showProductDetails?pid=${product.pid}" class="product-item md-height bg-gray d-block">
                        <img src="${cp}/img/${product.imgUrl}" alt="Image" class="img-fluid">
                    </a>
                    <h2 class="item-title"><a href="#">${product.name}</a></h2>
                    <strong class="item-price">$${product.price}</strong>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</div>
<!-- footer -->
<jsp:include page="include/foot.jsp"/>
</body>
</html>
