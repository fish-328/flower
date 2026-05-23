package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BaseDao {
	static ArrayList<Connection> list=new ArrayList<Connection>();
	private static final int MAX_LENGTH = 20;
	
	//synchronized 是 Java 中的关键字，利用锁的机制来实现同步，具有互斥性,保持单线程工作
	public synchronized static Connection getConn() {
		if (list.size()==0) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://localhost:3306/test"
				            + "?useUnicode=true&characterEncoding=UTF-8&&useSSL=false";
				for (int i = 1; i <=MAX_LENGTH ; i++) {
					Connection conn=DriverManager.getConnection(url,"root","root");
					list.add(conn);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//从连接池（list集合）中取出并移除第一个数据库连接对象
		return list.remove(0);
		
	}
	//rs 获取查询结果数据 ， ps 执行SQL操作（查询，插入，更新）
	public synchronized static void closeConn(ResultSet rs,PreparedStatement ps,Connection conn) {
		try {
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			
			if(conn!=null)
			{
				if(list.size()>MAX_LENGTH)	//连接数量如超过上限，则直接关闭
				{
					conn.close();
				}
				else					//连接数量如未超过上限，则归还至数据库连接池list中
				{
					list.add(conn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
