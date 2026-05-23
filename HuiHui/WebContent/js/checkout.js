// 页面加载时初始化
window.onload = function() {
    initCheckoutPage();
};

function initCheckoutPage() {
    console.log("结算页面初始化");
    validateCartItems();
}

// 验证购物车商品（防止直接访问结算页时购物车为空）
function validateCartItems() {
    const cartCount = document.getElementById('cart-count');
    if (cartCount && parseInt(cartCount.textContent) === 0) {
        alert('购物车为空，无法结算');
        setTimeout(() => {
            window.location.href = "cart.jsp";
        }, 1000);
    }
}

// 提交订单前验证表单
function validateOrderForm() {
    const form = document.getElementById('orderForm');
    const receiver = form.receiver.value.trim();
    const phone = form.phone.value.trim();
    const address = form.address.value.trim();
    
    if (!receiver) {
        alert('请填写收货人');
        return false;
    }
    
    if (!phone || !/^1[3-9]\d{9}$/.test(phone)) {
        alert('请填写正确的联系电话');
        return false;
    }
    
    if (!address) {
        alert('请填写收货地址');
        return false;
    }
    
    return true;
}

// 为提交按钮添加验证
document.getElementById('orderForm').addEventListener('submit', function(e) {
    if (!validateOrderForm()) {
        e.preventDefault(); // 阻止表单提交
    }
});