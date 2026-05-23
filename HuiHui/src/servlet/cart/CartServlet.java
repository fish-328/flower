package servlet.cart;

import bean.User;
import bean.cart;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    
	    // 如果用户未登录，重定向到登录页
	    if (user == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
        ProductDao dao = new ProductDao();
        List<cart> cartList = dao.findAllItems();
        
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    
	    
	    // 如果用户未登录，重定向到登录页
	    if (user == null) {
	        response.sendRedirect("login.jsp");
	        return;
	    }
        String action = request.getParameter("action");
        ProductDao dao = new ProductDao();
        
        try {
            switch (action) {
            case "addToCart":
                String product = request.getParameter("product");
                String quantityStr = request.getParameter("quantity"); // 获取字符串参数
                
                // 处理默认值并转换类型
                long quantity = 1; // 默认值
                if (quantityStr != null && !quantityStr.isEmpty()) {
                    try {
                        quantity = Long.parseLong(quantityStr); // 字符串转 long
                    } catch (NumberFormatException e) {
                        // 处理数字格式错误（如用户输入非数字）
                        response.sendRedirect("CartServlet?error=invalidQuantity");
                        return;
                    }
                }
                
                cart c = new cart();
                c.setProduct(product);
                c.setQuantity(quantity); // 传入 long 类型
                dao.insertOrUpdate(c);
                response.sendRedirect("CartServlet?status=added");
                break;
                    
                case "updateQuantity":
                    // 更新商品数量
                    String updateProduct = request.getParameter("product");
                    long newQuantity = Long.parseLong(request.getParameter("newQuantity"));
                    
                    dao.updateQuantity(updateProduct, newQuantity);
                    response.sendRedirect("CartServlet?status=updated");
                    break;
                    
                case "deleteItem":
                    // 删除商品
                    String deleteProduct = request.getParameter("product");
                    dao.deleteByProduct(deleteProduct); // 修正方法名
                    response.sendRedirect("CartServlet?status=deleted");
                    break;
                    
                case "clearCart":
                    // 清空购物车
                    dao.clearCart();
                    response.sendRedirect("CartServlet?status=cleared");
                    break;
                    
                default:
                    response.sendRedirect("CartServlet?error=unknownAction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("CartServlet?error=operationFailed");
        }
    }
}