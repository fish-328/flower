<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
<link rel="stylesheet" href="css/login.css" type="text/css" charset="UTF-8">
<script type="text/javascript" src="js/change.js" charset="UTF-8"></script>
</head>
<body>
    <div class="image">
        <img src="css/login.jpg" alt="花店">
    </div>
            
    <div class="login">
        <form action="changePasswordServlet" method="post" name="changePasswordForm" onsubmit="return submitForm();">
            <div class="header">修改密码</div>
            
            <!-- 全局错误消息 -->
            <c:if test="${not empty error}">
                <div class="errorInfo">${error}</div>
            </c:if>
            
            <div class="input">
                <div class="input-border">
                    <input type="password" class="border" name="oldPassword"
                         placeholder="请输入当前密码" required>
                </div>           
                <div class="errorInfo">${oldPasswordError}</div>
                
                <div class="input-border">
                    <input type="password" class="border" name="newPassword"
                          placeholder="请输入新密码" required >
                </div>
                <div class="errorInfo">${newPasswordError}</div>
                
                <div class="input-border">
                    <input type="password" class="border" name="confirmPassword"
                          placeholder="请再次输入新密码" required>
                </div>
                <div class="errorInfo">${confirmPasswordError}</div>
            </div>
            
            <div class="action">
                <button type="submit" class="btn">修改密码</button> 
            </div>
            
            <div>
                <a href="userCenter.jsp">返回用户中心</a>
            </div>        
        </form>    
    </div>    
</body>
</html>