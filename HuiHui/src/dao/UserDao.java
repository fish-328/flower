package dao;
import java.sql.*;
import bean.User;

public class UserDao extends BaseDao
{
	//查询
	public User findByPhone(String phone)
	{
		User u = null;
		Connection conn = getConn();
		String sql = "select * from flowerlogin where phone = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				u = new User(phone,rs.getString("password"));
			}
			//释放连接
			closeConn(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	//增加
	public boolean regUser(User u)
	{
		Connection conn = getConn();
		String sql = "insert into flowerlogin(phone,password) values(?,?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getPhone());
			ps.setString(2, u.getPassword());
			
			if(ps.executeUpdate()>0)
			{
				return true;
			}
			
			closeConn(null, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//更改
	public boolean updatePassword(User u)
	{

		Connection conn = getConn();
		String sql = "update flowerlogin set password=? where phone=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getPhone());			
			if(ps.executeUpdate()>0)
			{
				return true;
			}
			closeConn(null, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	//删除
	public boolean deleteUser(User u)
	{
		Connection conn = getConn();
		String sql = "delete from flowerlogin where phone=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getPhone());
			if (ps.executeUpdate()>0) {
				return true;
			}
			closeConn(null, ps, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}

