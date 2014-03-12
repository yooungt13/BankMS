package titan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import titan.bean.Login;

public class LoginDAO extends BaseDAO{
		private Login login = new Login();

		public ResultSet findAll() {
				// TODO Auto-generated method stub
				return super.findAll("login");
		}
		
		public Login findById( int id ){
				
				try {
					ResultSet rs = super.findById("login",id);
					while(rs.next()){
						login.setLoginid(rs.getInt(1));
						login.setPassword(rs.getString(2));
						login.setName(rs.getString(4));
						login.setType(rs.getInt(3));
						login.setDepartment(rs.getString(5));
						login.setBank(rs.getString(6));
					}					
					super.closeDAO();
					
					return login;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
		}
	
		
}
