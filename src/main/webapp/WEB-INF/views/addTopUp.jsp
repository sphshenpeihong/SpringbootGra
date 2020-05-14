d<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>充值界面</title>
    <meta name="description" content="">
    <meta name="author" content="templatemo">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/templatemo-style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweetalert.css">
    
    <%--<c:if test="${empty msg}">
        <script>
            swal(${msg}, "成功","success");
        </script>
    </c:if>--%>
</head>
<body>
<!-- Left column -->
<div class="templatemo-flex-row">
    <jsp:include page="sidebar.jsp"></jsp:include>
    <!-- Main content -->
    <div class="templatemo-content col-1 light-gray-bg">
        <%--<jsp:include page="goodsNav.jsp"></jsp:include>--%>
        <div class="templatemo-content-container">
            <div class="templatemo-content-widget white-bg">
                <h2 class="margin-bottom-10">充值功能</h2>
                <p>普通管理员充值</p>
                <form id="form" action="" class="templatemo-login-form" method="post">
                    <div class="row form-group">
                        <div class="col-lg-12 form-group">
                            <label class="control-label" >选择现有用户</label>
                            <select id="user" name="user">
                            </select>
                            <span id="money" style="margin-left: 20px;"></span>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-lg-12 form-group">
                            <label class="control-label" >充值金额</label>
                            <input type="text" class="form-control" id="topupMoney" name="topupMoney">
                        </div>
                    </div>
                    <div class="form-group text-right">
                        <input type="button" value="添加" class="templatemo-blue-button" onclick="clickGo()"/>
                        <%--<button type="submit" class="templatemo-blue-button" onclick="clickGo()">添加</button>--%>
                        <button type="reset" class="templatemo-white-button">重置</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>        <!-- jQuery -->
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>  --%>      <!-- jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>        <!-- Templatemo Script -->
<script type="text/javascript">
    var getResult = "";
    $(function () {
        //返回数据，并且初始化渲染页面
        $.ajax({
            url:"/shop/admin/getAllUser",
            type:"GET",
            success:function (result) {
                getResult = result;
                var userInfo = "";
                var username = "";
                if (result!=""){
                    for(var i=0;i<result.length;i++){
                        username = result[i].username;
                        userInfo += "<option value="+result[i].userid+">"+ username +"</option>"
                    }
                    var money = result[0].money;
                    $("#money").html("用户当前金额："+money);
                    $("#user").html(userInfo);
                }
            },
            error:function () {
                console.log("错误回调")
            }
        })

        //监听下拉框变换时，金额对应变换
        $("#user").on("change",function(){
            if(getResult!=""){
                for (var i=0;i<getResult.length;i++){
                    //遍历从后台拿的值与当前下拉框的值如果一直 则实现联动
                    var strA = $("#user option:checked").text();
                    var strB = getResult[i].username;
                    console.log(strA == strB)
                    if (strA == strB){
                        $("#money").html("用户当前金额："+getResult[i].money);
                        break;
                    }
                }
            }
        })


    })

    //点击添加调用充值接口
    function clickGo(){
        console.log("前")
        $("#form").val("${pageContext.request.contextPath}/admin/userTopUp.do");
        $.ajax({
            url:"${pageContext.request.contextPath}/admin/userTopUp.do",
            type:"post",
            data:{'topupMoney':$("#topupMoney").val(),'user':$("#user option:checked").val()},
            dataType:"json",
            success:function (result) {
                console.log("123")
                console.log(result)
                if (result.code==200){
                    alert(result.desc);
                    window.location.reload();
                }
            },
            error:function (result) {
                console.log("错误回调")
            }
        })
    }
</script>
</body>
</html>

