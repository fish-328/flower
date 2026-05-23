package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;

/**
 * Servlet implementation class Register
 */
@WebServlet({ "/RegisterServlet", "/registerServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
				String phone = request.getParameter("phone").trim();;
				String password = request.getParameter("password").trim();;
				String flag = request.getParameter("flag");
				
				UserDao uDao = new UserDao();
				//flag等于0：验证用户名是否存在
				if ("0".equals(flag)) {
					User u = uDao.findByPhone(phone);
					request.setAttribute("regPhoneExist", u!=null);
					request.setAttribute("regPhone", phone);
					RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
					disp.forward(request, response);
		}
					
	//flag等于1：数据库执行插入操作，完成注册
			if("1".equals(flag))
			{			
				
				PrintWriter out = response.getWriter();
				User u = new User(phone, password);
				if(uDao.regUser(u))
				{
					out.print("注册成功，3秒后自动跳转到登录页面！");
					response.setHeader("refresh", "3;url=login.jsp");
				}
				else
				{
					out.print("注册失败，3秒后自动跳转到注册页面！");
					response.setHeader("refresh", "3;url=register.jsp");
				}
			}
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
