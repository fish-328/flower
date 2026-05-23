<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户中心</title>
<link rel="stylesheet" href="css/index.css" type="text/css" charset="UTF-8">
<link rel="stylesheet" href="css/userCenter.css" type="text/css" charset="UTF-8">
</head>
<body>
<header>
    <h1>予花时</h1>
    <nav>
        <a href="index.jsp">首页</a>
        <a href="sort.jsp">分类</a>
        <a href="cart.jsp">购物车</a>
        <a href="userCenter.jsp">用户中心</a>
         <a href="logoutServlet" onclick="return confirm('确定要注销吗？')">注销</a>
    </nav>
</header>
<div class="container">
    <!-- 显示成功消息 -->
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="success-message">${sessionScope.successMessage}</div>
        <script>
            // 显示3秒后隐藏成功消息
            setTimeout(function() {
                document.querySelector('.success-message').style.display = 'none';
            }, 3000);
            // 移除Session中的消息
            document.addEventListener('DOMContentLoaded', function() {
                fetch('/clearSuccessMessage', {method: 'POST'});
            });
        </script>
    </c:if>
    
    <div class="header">
        <h2>用户中心</h2>
    </div>
    
    <!-- 从Session获取用户信息并动态渲染 -->
    <c:if test="${not empty sessionScope.user}">
        <div class="user-info">
            <div class="info-row">
                <div class="info-label">手机号:</div>
                <div class="info-value">${sessionScope.user.phone}</div>
            </div>
            <div class="info-row">
                <div class="info-label">密码:</div>
                <div class="info-value">••••••••••</div>
            </div>
        </div>
    </c:if>
    
    <div class="actions">
        <button onclick="location.href='changePassword.jsp'">修改密码</button>
        
    </div>
</div>
</body>
<script>
const deleteuser='<%=request.getAttribute("def")!=null?request.getAttribute("def"):""%>'
if(deleteuser.trim()!=="")
	{
		alert(deleteuser);
	}
</script>
</html>