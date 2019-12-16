<%--
  Created by IntelliJ IDEA.
  User: fanfan
  Date: 12/14/2019
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@ page import="com.marlabs.shopping.entity.User"%>

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

<%--<%--%>
<%--    if (session.getAttribute("currentUser") == null) {--%>
<%--        User user = new User();--%>
<%--        user.setUid(-1);--%>
<%--        session.setAttribute("currentUser",user);--%>
<%--    }--%>
<%--%>--%>

<!--nav-bar-->
<jsp:include page="include/header.jsp"/>

<!-- body -->
<div class="site-wrap">
<div class="bg-light py-3">
    <div class="container">
        <div class="row">
            <div class="col-md-12 mb-0">
                <a href="${cp}/getAllProducts">Home</a>
                <span class="mx-2 mb-0">/</span>
                <strong class="text-black">${product.name}</strong></div>
        </div>
    </div>
</div>

<div class="site-section">
    <div class="container">
        <div class="row">
            <!-- left picture-->
            <div class="col-md-6">
                <div class="item-entry">
                    <a href="#" class="product-item md-height bg-gray d-block">
                        <img src="${cp}/img/${product.imgUrl}" alt="Image" class="img-fluid">
                    </a>
                </div>
            </div>


            <div class="col-md-6">
                <h2 class="text-black">${product.name}</h2>
                <p>${product.description}</p>
                <p><strong class="text-primary h4">$${product.price}</strong></p>
                <p>There is only ${product.stock} in stock!</p>
                <div class="mb-5">
                    <div class="input-group mb-3" style="max-width: 120px;">
                        <div class="input-group-prepend">
                            <button onclick="" class="btn btn-outline-primary js-btn-minus" type="button">&minus;</button>
                        </div>
                        <input type="text" id="productAmount" class="form-control text-center" value="1">
                        <div class="input-group-append">
                            <button onclick="" class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
                        </div>
                    </div>
                </div>
                <button onclick="addToShoppingCart(${currentUser.uid}, ${product.pid})" class="buy-now btn btn-sm height-auto px-4 py-3 mr-4 btn-primary">Add To Cart</button>
                <button onclick="addToWishList(${currentUser.uid}, ${product.pid})" class="buy-now btn btn-sm height-auto px-4 py-3 mr-4 btn-primary">Add To WishList</button>

            </div>
        </div>
    </div>
</div>
<script>

    <%--function subAmount() {--%>
    <%--    var productAmount = document.getElementById("productAmount").value;--%>
    <%--    var amount = parseInt(productAmount);--%>
    <%--    if(amount >= 2) amount--;--%>
    <%--    document.getElementById("productAmount").value = amount;--%>
    <%--}--%>

    <%--function addAmount() {--%>
    <%--    var productAmount = document.getElementById("productAmount").value;--%>
    <%--    var amount = parseInt(productAmount);--%>
    <%--    if(amount < ${product.stock}) amount++;--%>
    <%--    document.getElementById("productAmount").value = amount;--%>
    <%--}--%>

    function addToShoppingCart(uid, pid) {
        var productAmount = document.getElementById("productAmount");
        var amount = parseInt(productAmount.value);
        var shoppingCart = {};
        shoppingCart.uid = uid;
        shoppingCart.pid = pid;
        shoppingCart.quantity = amount;
        var addResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/addShoppingCart',
            data : shoppingCart,
            dataType : 'json',
            success : function(result) {
                addResult = result.result;
            },
            error : function(result) {
                // addResult = result.result;
                layer.alert('Fail to connect to Server');
            }
        });
        if(addResult == "success") {
            layer.msg('Success!',{icon:1});
        }
    }

    function addToWishList(uid, pid) {
        if (uid == -1) {
            layer.msg('Please login to visit WishList!',{icon:2});
            return;
        }
        var productAmount = document.getElementById("productAmount");
        var amount = parseInt(productAmount.value);
        var wishList = {};
        wishList.uid = uid;
        wishList.pid = pid;
        wishList.quantity = amount;
        var addResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/addWishList',
            data : wishList,
            dataType : 'json',
            success : function(result) {
                addResult = result.result;
            },
            error : function(result) {
                layer.alert('Fail to connect to Server to add to wishList');
            }
        });
        if(addResult == "success") {
            layer.msg('Success!',{icon:1});
        }
    }
</script>
<!-- footer -->
<jsp:include page="include/foot.jsp"/>
</body>
</html>
