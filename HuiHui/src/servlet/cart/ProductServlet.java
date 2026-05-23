package servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import bean.cart;
import dao.ProductDao;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    
	    // 如果用户未登录，重定向到登录页
	    if (user == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
		String product_id=request.getParameter("product_id");

		cart c=new cart();
		c.setProduct(product_id);
		ProductDao p=new ProductDao();
		
        
		if (p.insertOrUpdate(c)) {
			request.setAttribute("a","成功");
			request.getRequestDispatcher("product.jsp").forward(request, response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
