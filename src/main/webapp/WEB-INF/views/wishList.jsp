<%--
  Created by IntelliJ IDEA.
  User: fanfan
  Date: 12/15/2019
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

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
<!-- WishList Header -->
<div class="bg-light py-3">
    <div class="container">
        <div class="row">
            <div class="col-md-12 mb-0"><a href="${cp}/getAllProducts">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">WishList</strong></div>
        </div>
    </div>
</div>

<div class="site-section">
    <div class="container">
        <div class="row mb-5">
            <form class="col-md-12" method="post">
                <div class="site-blocks-table">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th class="product-thumbnail">Image</th>
                            <th class="product-name">Product</th>
                            <th class="product-price">Price</th>
                            <th class="product-quantity">Quantity</th>
                            <th class="product-total">Total</th>
                            <th class="product-remove">Modify</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${productInWishList}" var="productToBuy">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="${cp}/showProductDetails?pid=${productToBuy.pid}">
                                        <img src="${cp}/img/${productToBuy.imgUrl}" alt="Image" class="img-fluid" style="max-height: 200px;">
                                    </a>
                                </td>
                                <td class="product-name">
                                    <h2 class="h5 text-black">${productToBuy.name}</h2>
                                </td>
                                <td>$${productToBuy.price}</td>
                                <td>
                                    <div class="input-group mb-3" style="max-width: 120px;">
                                        <div class="input-group-prepend">
                                            <button class="btn btn-outline-primary js-btn-minus" type="button">&minus;</button>
                                        </div>
                                        <input type="text" class="form-control text-center" value="${productToBuy.quantity}" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1">
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
                                        </div>
                                    </div>

                                </td>
                                <td>$${productToBuy.price * productToBuy.quantity}</td>
                                <td>
                                    <p><button class="btn btn-primary height-auto btn-sm" onclick="deleteWishList(${productToBuy.pid})">Delete Items</button></p>
                                    <p><button class="btn btn-primary height-auto btn-sm" onclick="moveToCart(${productToBuy.pid})">Move to ShoppingCart</button></p>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- footer -->
<jsp:include page="include/foot.jsp"/>

<script>
    function deleteWishList(pid) {
        var wishList = {};
        wishList.uid = ${currentUser.uid};
        wishList.pid = pid;
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/deleteWishList',
            data : wishList,
            dataType : 'json',
            success : function(result) {
                deleteResult = result.result;
            },
            error : function(result) {
                layer.alert('Fail to connect to server to delete wishList');
            }
        });
        if(deleteResult == 'success'){
            layer.msg('Delete Success!',{icon:1});
            window.location.href="${cp}/getAllWishLists";
        }
    }

    function moveToCart(pid) {
        var wishList = {};
        wishList.uid = ${currentUser.uid};
        wishList.pid = pid;
        var moveToCartResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/moveToCart',
            data : wishList,
            dataType : 'json',
            success : function(result) {
                moveToCartResult = result.result;
            },
            error : function(result) {
                layer.alert('Fail to connect to server to move to cart');
            }
        });
        if(moveToCartResult == 'success'){
            layer.msg('Move To Cart Success!',{icon:1});
            window.location.href="${cp}/getAllWishLists";
        }
    }


</script>
</body>
</html>