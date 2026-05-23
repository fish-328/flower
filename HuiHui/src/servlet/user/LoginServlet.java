package servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;

@WebServlet({ "/LoginServlet", "/loginServlet" }) 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code").trim();;
		
		HttpSession session = request.getSession();
		String rand = (String)session.getAttribute("rand");

		String phone = request.getParameter("phone").trim();
		String password = request.getParameter("password").trim();
		
		UserDao uDao = new UserDao();
		User u = uDao.findByPhone(phone);
			
		if(rand.equals(code))
		{			
			if(u==null)
			{
				request.setAttribute("PhoneError", "手机号不存在！！！");
			}
			else if(!u.getPassword().equals(password))
			{
				request.setAttribute("PasswordError", "密码错误！！！");
			}
			else
			{			
				//操作1：在session存入对象u，作为合法登录的标记
				session.setAttribute("user", u);
				
				//操作2：重定向跳转至主页面
				response.sendRedirect("index.jsp");
				return;
			}
		}
		else
		{
			request.setAttribute("codeError", "验证码错误！！！");			
		}
		
		request.setAttribute("errorUser", u);
		
		RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}