// 实时校验新密码长度
    function checkNewPassword() {
        const newPwd = document.querySelector('input[name="newPassword"]').value;
        if (newPwd.length > 0 && newPwd.length < 6) {
            alert('新密码长度不能少于6位，请重新输入！');
            // 也可以选择清空输入框，让用户重新输入
            document.querySelector('input[name="newPassword"]').value = "";
            return false; // 阻止后续默认行为（如果需要）
        }
        return true;
    }

    // 表单提交前统一校验
    function submitForm() {
        const isNewPwdValid = checkNewPassword();
        // 还可以补充其他校验，比如确认密码和新密码是否一致等
        const confirmPwd = document.querySelector('input[name="confirmPassword"]').value;
        const newPwd = document.querySelector('input[name="newPassword"]').value;
        if (confirmPwd !== newPwd) {
            alert('两次输入的新密码不一致，请检查！');
            return false;
        }
        return isNewPwdValid;
    }