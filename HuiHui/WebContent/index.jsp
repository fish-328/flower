<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<link rel="stylesheet" href="css/index.css" type="text/css" charset="UTF-8">

</head>
<body>
	<header>
		<h1>予花时</h1>
		<!-- 导航条 -->
		<nav>
			<a href="index.jsp">首页</a>
			<a href="sort.jsp">分类</a>
			<a href="cart.jsp">购物车</span></a>
			
    		<c:if test="${empty sessionScope.user}">
    		<a href="login.jsp">登录</a>
    		<a href="register.jsp">注册</a>
            </c:if>
    		<c:if test="${not empty sessionScope.user}">
        	<a href="userCenter.jsp">用户中心</a>
        	<a href="logoutServlet" onclick="return confirm('确定要注销吗？')">注销</a>
       		</c:if>
       		 
		</nav>
	</header>
	<!-- 布局 -->
    <section class="banner">
        <div class="banner-text">
        	
            <h2>予花时 · 让美好绽放</h2>
            <p>甄选全球鲜材 · 定制专属心意</p >
            
        </div>
    </section>

    <section class="feature">
        
            <div class="feature-item">
                <img alt="极速配送" src="css/index1.png">
                <h3>极速配送</h3>
                <p>  全城3小时达      </p >
            </div>
            <div class="feature-item">
                <img alt="专属定制" src="css/index2.jpg">
                <h3>专属定制</h3>
                <p> 花艺师1v1设计  </p >
            </div>
            <div class="feature-item">
                <img alt="永生花礼" src="css/index1.png">
                <h3>永生花礼</h3>
                <p>留住瞬间 浪漫永存</p >
            </div>
        
    </section>

    <!-- 底部信息  -->
    <footer>
        <p> 2025 予花时 版权所有 | 联系方式：400-1234-1234</p>
    </footer>
</body>
</html>
