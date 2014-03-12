package titan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
		private String driver = "com.mysql.jdbc.Driver";
		private String userName = "root";
		private String passwrod = "123";
		private String url = "jdbc:mysql://localhost:3306/bankms";
		private Connection conn;
		protected Statement stms;
		
		protected BaseDAO(){			
				try {
					Class.forName(driver);
					conn = DriverManager.getConnection(url, userName, passwrod);
					stms = conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}            
		}
		
		public ResultSet findAll( String table ){
				try {
					String sql = "select * from  " + table;
					return stms.executeQuery(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;		
				}				
		}

		public ResultSet findById(String table, int id) {
				try {
					String sql = "SELECT * FROM " + table + " WHERE id=" + id;
				//"select * from " + table + " where id=" + id;
					return stms.executeQuery(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;		
				}	
		}
		
		public int add( String table, String values ){
				try {
					ResultSet rs = stms.executeQuery("select max(id) as id from "+table);   //查询出最大ID
					int id = 1;
					if( rs.next() ){//如果有ID，原有ID+1
					   id = rs.getInt("id") + 1;
					}
					String sql = "insert into " + table + " values(" + id + ", " + values + ")";
					stms.execute(sql);
					return id;		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}		
		}
		
		public void update( String table, int id, Float values ){
				try {
					String sql = "update " + table + " set balance=" + values + " where id ="+ id;
					stms.execute(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
		
		public void closeDAO(){		
				try {
					stms.close();			//关闭statement
					conn.close(); 			//关闭数据库连接
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

		}

}
