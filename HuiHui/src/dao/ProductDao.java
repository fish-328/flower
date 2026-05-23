package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.cart;

public class ProductDao extends BaseDao {
    // 原有的 findByProduct 方法保持不变
    public cart findByProduct(String product) {
        Connection conn = getConn();
        String sql ="SELECT * FROM cart WHERE product = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cart c = new cart();
                    c.setProduct(rs.getString("product"));
                    c.setQuantity(rs.getLong("quantity"));
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 原有的 insertOrUpdate 方法保持不变
    public boolean insertOrUpdate(cart c) {
        Connection conn = getConn();
        // 先查询商品是否已存在
        cart existingCart = findByProduct(c.getProduct());
        
        if (existingCart != null) {
            // 商品已存在，执行更新操作（数量+1）
            String sql = "UPDATE cart SET quantity = quantity + 1 WHERE product = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getProduct());
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // 商品不存在，执行插入操作（数量固定为1）
            String sql = "INSERT INTO cart(product, quantity) VALUES(?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, c.getProduct());
                ps.setLong(2, 1); // 强制设置初始数量为1
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 新增方法：查询所有购物车项
    public List<cart> findAllItems() {
        List<cart> cartList = new ArrayList<>();
        Connection conn = getConn();
        String sql = "SELECT * FROM cart";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cart c = new cart();
                c.setProduct(rs.getString("product"));
                c.setQuantity(rs.getLong("quantity"));
                cartList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    // 新增方法：根据商品名删除购物车项
    public boolean deleteByProduct(String product) {
        Connection conn = getConn();
        String sql = "DELETE FROM cart WHERE product = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 新增方法：清空购物车
    public boolean clearCart() {
        Connection conn = getConn();
        String sql = "DELETE FROM cart";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            return ps.executeUpdate() >= 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 新增方法：更新商品数量（直接设置为指定值）
    public boolean updateQuantity(String product, long quantity) {
        Connection conn = getConn();
        String sql = "UPDATE cart SET quantity = ? WHERE product = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, quantity);
            ps.setString(2, product);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 新增方法：获取购物车商品总数
    public long getTotalItems() {
        Connection conn = getConn();
        String sql = "SELECT SUM(quantity) FROM cart";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}