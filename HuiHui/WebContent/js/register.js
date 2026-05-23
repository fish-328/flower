//验证注册用户名是否可用
function checkRegPhone() {
	var phone = document.regForm.phone.value;
	if(phone=="")
	{
		alert("用户名不能为空！！！");
		document.regForm.phone.focus();
		return false;
	}
	document.regForm.flag.value = "0";
	document.regForm.submit();
	
	return true;
}

//验证注册表单，并提交给RegServlet完成注册操作
function checkReg() {
	var phone = document.regForm.phone.value;
	var password = document.regForm.password.value;
	var password2 = document.regForm.password2.value;
	
	if(phone=="")
	{
		alert("手机号不能为空！！！");
		document.regForm.phone.focus();
		return false;
	}
	if(pwd=="")
	{
		alert("密码不能为空！！！");
		document.regForm.password.focus();
		return false;
	}
	if(pwd2=="")
	{
		alert("确认密码不能为空！！！");
		document.regForm.password2.focus();
		return false;
	}
	if(password!=password2)
	{
		alert("密码不一致！！！");
		document.regForm.password.value = "";
		document.regForm.password2.value = "";
		document.regForm.password.focus();
		return false;
	}
	
	document.regForm.flag.value = "1";
	document.regForm.submit();
	return true;
}

//重新跳转至reg.jsp页面，进而清空所有信息
function resetForm() {
	window.location.replace("register.jsp");
}