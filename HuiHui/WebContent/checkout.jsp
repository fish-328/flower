<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>结算页面</title>
    <link rel="stylesheet" href="css/checkout.css">
    <script src="js/checkout.js" charset="UTF-8"></script>
</head>
<body>
    <header>
        <h1>予花时</h1>
        <nav>
            <a href="index.jsp">首页</a>
            <a href="cart.jsp">购物车</a>
            <a href="checkout.jsp">结算</a>
        </nav>
    </header>

    <section class="checkout-container">
        <h2>订单结算</h2>
        
        
        <!-- 订单信息 -->
        <section class="order-info">
            <h3>订单信息</h3>
            <form id="orderForm" action="OrderServlet" method="post">
                <div class="form-group">
                    <label for="receiver">收货人</label>
                    <input type="text" id="receiver" name="receiver" required>
                </div>
                <div class="form-group">
                    <label for="phone">联系电话</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="address">收货地址</label>
                    <input type="text" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="remark">备注信息</label>
                    <textarea id="remark" name="remark"></textarea>
                </div>
                
            </form>
        </section>
    </section>
</body>
</html>