package servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;

@WebServlet("/changePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ChangePasswordServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 request.setCharacterEncoding("UTF-8");
    	    
    	    // 获取当前登录用户
    	    HttpSession session = request.getSession();
    	    User user = (User) session.getAttribute("user");
    	    
    	    // 如果用户未登录，重定向到登录页
    	    if (user == null) {
    	        response.sendRedirect("login.jsp");
    	        return;
    	    }
    	    
    	    // 获取表单参数
    	    String oldPassword = request.getParameter("oldPassword");
    	    String newPassword = request.getParameter("newPassword");
    	    String confirmPassword = request.getParameter("confirmPassword");
    	    
    	    System.out.println("接收到修改密码请求：oldPassword=" + oldPassword 
    	                      + ", newPassword=" + newPassword 
    	                      + ", confirmPassword=" + confirmPassword);
    	    
    	    boolean hasError = false;
    	    
    	    // 验证当前密码
    	    if (oldPassword == null || oldPassword.isEmpty()) {
    	        System.out.println("旧密码为空，设置错误提示");
    	        request.setAttribute("oldPasswordError", "请输入当前密码");
    	        hasError = true;
    	    } else if (!oldPassword.equals(user.getPassword())) {
    	        System.out.println("旧密码不正确，设置错误提示");
    	        request.setAttribute("oldPasswordError", "当前密码不正确");
    	        hasError = true;
    	    }
    	    
    	    // 验证新密码
    	    if (newPassword == null || newPassword.isEmpty()) {
    	        System.out.println("新密码为空，设置错误提示");
    	        request.setAttribute("newPasswordError", "请输入新密码");
    	        hasError = true;
    	    } else if (newPassword.length() < 6) {
    	        System.out.println("新密码长度小于6，设置错误提示");
    	        request.setAttribute("newPasswordError", "新密码长度不能少于6位");
    	        hasError = true;
    	    }
    	    
    	    // 验证确认密码
    	    if (confirmPassword == null || confirmPassword.isEmpty()) {
    	        System.out.println("确认密码为空，设置错误提示");
    	        request.setAttribute("confirmPasswordError", "请确认新密码");
    	        hasError = true;
    	    } else if (!confirmPassword.equals(newPassword)) {
    	        System.out.println("两次输入不一致，设置错误提示");
    	        request.setAttribute("confirmPasswordError", "两次输入的新密码不一致");
    	        hasError = true;
    	    }
    	    
    	    if (hasError) {
    	        System.out.println("存在错误，转发回修改密码页面");
    	        // 有错误，转发回修改密码页面
    	        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    	        return;
    	    }
    	    
    	    // 更新密码
    	    try {
    	        UserDao userDao = new UserDao();
    	        
    	        // 更新数据库中的密码
    	        user.setPassword(newPassword);
    	        userDao.updatePassword(user);
    	        
    	        // 更新Session中的用户信息
    	        session.setAttribute("user", user);
    	        
    	        // 设置成功消息
    	        session.setAttribute("successMessage", "密码修改成功");
    	        
    	        // 重定向到用户中心
    	        response.sendRedirect("userCenter.jsp");
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        request.setAttribute("error", "修改密码失败，请稍后再试");
    	        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    	    }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    doPost(request, response);
}
}