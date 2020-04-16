<%@page import="java.security.interfaces.RSAKey"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>购物商城-欢迎登录</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sort.js"></script>
<script src="${pageContext.request.contextPath}/js/holder.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/login.js"></script>
</head>



<body>
	<%
		Cookie[] cookies = request.getCookies(); //获取后台传回前端的cookie 数组类型 可能会有许多个
        System.out.println("response的值："+response.getHeader("head")); //获得本次请求响应头的值
        request.getSession(); //获得本次请求的相关信息（获得request域的东西）
	%>
    <div>
        <c:forEach items="<%=cookies%>" var="cookie">
            <!-- 获得的是一个js对象 使用.的形式去取得js对象里面key所对应的值 -->
            ${cookie.userCookie.getValue()}<br/>
        </c:forEach>
    </div>
</body>
</html>