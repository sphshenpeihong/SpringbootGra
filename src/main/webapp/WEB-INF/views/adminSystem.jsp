<%@page import="java.security.interfaces.RSAKey"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet'
          type='text/css'>
    <!-- JS -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/adminSystem.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweetalert.css">
    <%--<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>--%>
</head>



<body>
1232
<div class="templatemo-flex-row">
    <jsp:include page="sidebar.jsp"/>
    212322
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
            <div class="row">
                <nav class="templatemo-top-nav col-lg-12 col-md-12">
                    <ul class="text-uppercase">
                        <li><a href="${pageContext.request.contextPath}/admin/system" class="active">所有后台管理员</a></li>
                        <c:if test="${isSuper == 1}"><li><a href="${pageContext.request.contextPath}/admin/addManager.do" class="active">添加普通管理员</a></li></c:if>
                        <li><a href="${pageContext.request.contextPath}/admin/activity/add">充值功能</a></li>
                        <%--<li><a href="${pageContext.request.contextPath}/admin/goods/addCategory">添加分类</a></li>--%>
                        <%--<li><a href="login.html">。。。</a></li>--%>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="templatemo-content-container">
            <div class="templatemo-content-widget no-padding">
                <div class="panel panel-default table-responsive">
                    <table id="goodsinfo" class="table table-striped table-bordered templatemo-user-table">
                        <thead>
                        <tr>
                            <td><a href="" class="white-text templatemo-sort-by">id<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">用户名<span class="caret"></span></a></td>
                            <td><a href="" class="white-text templatemo-sort-by">权限<span class="caret"></span></a></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${isSuper == 1}">
                        <c:forEach items="${allManager}" var="manager" varStatus="num">
                            <tr>
                                <td>${manager.adminid}</td>
                                <td>${manager.adminname}</td>
                                <c:choose>
                                    <c:when test="${manager.authority == 1}">
                                        <td>超管</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>普管管</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>