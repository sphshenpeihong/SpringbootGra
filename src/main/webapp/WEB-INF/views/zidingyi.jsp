<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweetalert.css">
    <%--<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>--%>

</head>
<body>
<%--<form action="${pageContext.request.contextPath}/zidingyi111.do" method="post">
    <p><input type="checkbox" name="category" value="今日话题" />今日话题 </p>
    <p><input type="checkbox" name="category" value="视觉焦点" />视觉焦点</p>
    <p><input type="checkbox" name="category" value="财经" />财经</p>
    <p><input type="checkbox" name="category" value="汽车" />汽车</p>
    <p><input type="checkbox" name="category" value="科技" />科技</p>
    <p><input type="checkbox" name="category" value="房产" />房产</p>
    <p><input type="checkbox" name="category" value="旅游" />旅游</p>
    <p><input type="submit" value="确定了" /></p>
</form>--%>

<form action="${pageContext.request.contextPath}/zidingyi111.do" method="post">
    <p><input type="text" name="username" value="123"></p>
    <p><input type="text" name="username" value="456"></p>
    <p><input type="submit" value="确定"></p>
</form>

</body>
</html>
