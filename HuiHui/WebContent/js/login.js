//通过再次调用ValidateCode（其url为before_validateCode），刷新验证码
//Math.random()使每次的刷新的url值不同，确保刷新操作有效。
function refreshCode() {
	document.getElementById("code").src = "before_validateCode?t="
			+ Math.random();
}