<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/love.css">
    
</head>
<body>
    <header>
        <h1>予花时</h1>
        <nav>
            <a href="index.jsp">首页</a>
            <a href="sort.jsp">分类</a>
            <a href="CartServlet">购物车</a>

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

    <section class="valentine-banner">
        <h2>予爱繁花 · 浪漫朝夕</h2>
        <p>情人节限定花束，诉说心动告白</p>
    </section>

    <!-- 花束列表 -->
    <section class="valentine-flowers">
<form action="ProductServlet">
        <div class="flower-card" data-product-id="1" data-product-price="299">
            <img src="css/f1.jpg" alt="心动玫瑰">
            <div class="flower-info">
                <h3>心动玫瑰</h3>
                <p class="price">¥ 299</p>
                <input type="hidden" name="product_id" value="心动玫瑰">
                <button class="order-btn" onclick="checkLoginAndAddToCart(1)" type="submit">立即预定</button>
            </div>
        </div>
</form>
<form action="ProductServlet">
        <div class="flower-card" data-product-id="2" data-product-price="399">
            <img src="css/f2.jpg" alt="永恒之心">
            <div class="flower-info">
                <h3>永恒之心</h3>
                <p class="price">¥ 399</p>
                <input type="hidden" name="product_id" value="永恒之心">
                <button class="order-btn" onclick="checkLoginAndAddToCart(2)" type="submit">立即预定</button>
            </div>
        </div>
</form>
<form action="ProductServlet">
        <div class="flower-card" data-product-id="3" data-product-price="199">
            <img src="css/f3.jpg" alt="蜜恋绣球">
            <div class="flower-info">
                <h3>蜜恋绣球</h3>
                <p class="price">¥ 199</p>
                <input type="hidden" name="product_id" value="蜜恋绣球">
                <button class="order-btn" onclick="checkLoginAndAddToCart(3)" type="submit">立即预定</button>
            </div>
        </div>
</form>
    </section>

    
</body>
<script>
const add='<%=request.getAttribute("a")!=null?request.getAttribute("a"):""%>'
if(add.trim()!=="")
	{
		alert(add);
	}
</script>
</html>