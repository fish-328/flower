<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册界面</title>
	<link rel="stylesheet" href="css/register.css" type="text/css" charset="UTF-8"/>
	<script type="text/javascript" src="js/register.js" charset="UTF-8"></script>		
</head>
<body>
	<div class="image">
			<img src="css/login.jpg" alt="花店">
		</div>
		
		<form action="registerServlet" method="post" name="regForm">
			<%-- 问题：此处隐藏域中的参数flag有什么作用？ 帮后端regServlet判断注册请求的处理方式--%>
				
			<input type="hidden" name="flag" value="1">
			
			<div class="login">
				<div class="header">欢迎您，请注册</div>
				<div class="input">
					<%-- 输入注册的用户名，文本框失去焦点后会触发执行checkRegPhone()，自动判定该手机号是否可用 --%>	
					<div class="input-border">
						<input type="text" class="border" name="phone"
							value="${regPhone}" onblur="checkRegPhone()" placeholder="请输入手机号">
					</div>
					
					<%-- 通过RegisterServlet中存放的regPhoneExist，判定该用户名是否可用 --%>	
					<div class="mark">
						<%-- JSTL标签 --%>
						<c:if test="${requestScope.regPhoneExist}">
							<font color="#FF0000">该手机号已存在！</font>
						</c:if>
						<c:if test="${!requestScope.regPhoneExist}">
							<font color="#00FF00">该手机号可使用！</font>
						</c:if>
					</div>
					
					<%-- 第一次输入密码 --%>
					<div class="input-border">
						<input type="password" class="border" name="password" placeholder="请输入密码">
					</div>
					
					<%-- 第二次输入密码 --%>
					<div class="input-border">
						<input type="password" class="border" name="password2" placeholder="请再次输入密码">
					</div>					
				</div>
				
				<div class="action">
					<%-- 通过onclick属性，调用javascript函数resetForm(); --%>	
					<button class="btn" onclick="return resetForm()">清空</button>
					<button class="btn" onclick="return checkReg()">注册</button>
				</div>
				
			</div>
		</form>
</body>
</html>