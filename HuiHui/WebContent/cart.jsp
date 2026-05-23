<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" href="css/index.css" type="text/css" charset="UTF-8">
    <link rel="stylesheet" href="css/cart.css" type="text/css" charset="UTF-8">
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
<!-- 用 cart-container 包裹购物车内容，方便统一控制样式 -->
<div class="cart-container">
    <h1>我的购物车</h1>
    <c:if test="${not empty cartList}">
        <table class="cart-table">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartList}" var="item">
                <tr>
                    <td class="product-name">${item.product}</td>
                    <td>
                        <!-- 数量修改表单，用 quantity-group 做容器优化布局 -->
                        <div class="quantity-group">
                            <form action="CartServlet" method="post">
                                <input type="hidden" name="action" value="updateQuantity">
                                <input type="hidden" name="product" value="${item.product}">
                                <input type="number" name="newQuantity" value="${item.quantity}" min="1">
                                <button type="submit">修改</button>
                            </form>
                        </div>
                    </td>
                    <td>
                        <!-- 删除表单 -->
                        <form action="CartServlet" method="post">
                            <input type="hidden" name="action" value="deleteItem">
                            <input type="hidden" name="product" value="${item.product}">
                            <button type="submit" class="delete-btn">删除商品</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- 结算区域 -->
        <div class="checkout-section">
            <a href="checkout.jsp" class="checkout-btn">去结算</a>
        </div>
    </c:if>
    <c:if test="${empty cartList}">
        <div class="empty-cart">
            <p>购物车为空，<a href="sort.jsp">去选购</a>吧～</p>
        </div>
    </c:if>
</div>
</body>
</html>