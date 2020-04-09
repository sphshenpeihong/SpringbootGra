<%@page import="java.security.interfaces.RSAKey"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>华广商城-欢迎登录</title>
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
<%--<script>

	$(document).ready(function() {

		
		$(document).keypress(function(e) {
			if (e.which == 13) {
				e.preventDefault();
				
				$(".login-input").click();
				//form.submit();
			}
		});
		input_value();
	})
	
	function input_value() {
		if (UserID != "null") {
			document.getElementById("inputEmail3").value = UserID;
		}
	}
</script>--%>




<body>
	测试页面
</body>
</html>