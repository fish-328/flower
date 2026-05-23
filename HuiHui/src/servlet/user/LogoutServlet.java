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

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            request.setAttribute("error", "未登录或会话已过期");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // 从user对象中获取phone
        User user = (User) session.getAttribute("user");
        if (user == null || user.getPhone() == null) {
            request.setAttribute("error", "未登录或会话已过期");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        String phone = user.getPhone();
        User u = new User();
        u.setPhone(phone);
        UserDao dao = new UserDao();
        if (dao.deleteUser(u)) {
            session.invalidate(); // 删除成功后销毁会话
            request.getRequestDispatcher("LogoutSuccess.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "注销失败：未找到匹配的手机号");
            request.getRequestDispatcher("userCenter.jsp").forward(request, response);
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
