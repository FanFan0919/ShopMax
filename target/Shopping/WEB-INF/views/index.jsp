<%--
  Created by IntelliJ IDEA.
  User: fanfan
  Date: 12/14/2019
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>ShopMax</title>
</head>
<script type="text/javascript">
    getAllProducts();
    function getAllProducts() {
        location.href = "${cp}/initializeCart";
    }
</script>
</html>