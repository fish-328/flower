<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link rel="stylesheet" href="css/login.css" type="text/css" charset="UTF-8">
<script type="text/javascript" src="js/login.js" charset="UTF-8"></script>
</head>
<body>
	<div class="image">
		<img src="css/login.jpg" alt="花店">
	</div>

	<div class="login">
		<form action="LoginServlet" method="post" name="loginForm">
			<div class="header">欢迎您，请登录</div>



			<div class="input">
				<div class="input-border">
					<input type="text" class="border" name="phone" placeholder="请输入手机号"
						value="${errorUser.phone }" required>
				</div>
				<div class="errorInfo">${PhoneError}</div>

				<div class="input-border">
					<input type="password" class="border" name="password"
						placeholder="请输入密码" value="${errorUser.password }" required>
				</div>
				<div class="errorInfo">${PasswordError}</div>
				<%-- 验证码区域（表格布局） --%>
	<table style="width: 100%; border-collapse: collapse;">
   	 	<tr>
        	<td style="width: 60%; padding: 0;">
            	<div class="input-border">
                <input type="text" class="border" name="code" placeholder="输入验证码" 
                       style="box-sizing: border-box; width: 100%;">
           		</div>
        	</td>
        	<td style="width: 40%; padding: 0;">
            	<div class="input-border">
                	<a href="javascript:refreshCode();">
                    	<img id="code" src="before_validateCode" alt="看不清换一张"
                         style="box-sizing: border-box; width: 100%; height: 100%;">
                	</a>
            	</div>
        	</td>
    	</tr>
	</table>
	<div class="errorInfo">${codeError}</div>

			</div>

			<div class="action">
				<button type="submit" class="btn">登录</button>
			</div>

			<div>
				<a href="register.jsp">注册账号</a>
			</div>
		</form>
	</div>
</body>


</html>