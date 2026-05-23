<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>鲜花主页</title>
<link rel="stylesheet" href="css/sort.css" type="text/css" charset="UTF-8">
<script type="text/javascript" src="js/index.js" charset="UTF-8"></script>
</head>
<body>
    <header>
		<h1>予花时</h1>
		<!-- 导航条 -->
		<nav>
			<a href="index.jsp">首页</a>
			<a href="sort.jsp">分类</a>
			<a href="cart.jsp">购物车 </a>
			
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
    <!-- 分类网格布局 -->
    <section class="category-grid">
        <div class="category-card">
            <img alt="送恋人" src="css/sort1.jpg">
            <h3>送恋人</h3>
            <p>鲜花我选 说爱你</p>
            <a href="product.jsp" class="btn">GO</a>
        </div>
        <div class="category-card">
            <img alt="送长辈" src="css/sort2.jpg">
            <h3>送长辈</h3>
            <p>你在 我即安心</p>
            <a href="product.jsp" class="btn">GO</a>
        </div>
        <div class="category-card">
            <img alt="送朋友" src="css/sort3.jpg">
            <h3>送朋友</h3>
            <p>见花如面 快乐加倍</p>
            <a href="product.jsp" class="btn">GO</a>
        </div>
    </section>
</body>
</html>