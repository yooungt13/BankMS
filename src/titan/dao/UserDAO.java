package titan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import titan.bean.Account;
import titan.bean.User;

public class UserDAO extends BaseDAO{
	
	public ArrayList<User> findUserByAccount( Account ac ){
		try {
			String sql = "select * from user where id in (select user_id from relationship where ac_id=" + ac.getId()+")";
			ResultSet rs = stms.executeQuery(sql);
			ArrayList<User> userList = new ArrayList();
			
			while(rs.next()){
					User tmp = new User();
					tmp.setId(rs.getInt(1));
					tmp.setUsername(rs.getString(2));
					tmp.setPassword(rs.getString(3));
					tmp.setIdentifier(rs.getString(4));
					userList.add(tmp);
			}					
			
			return userList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	public User findById( int id ){
		try{
			ResultSet rs = super.findById("user", id);
			User user = new User();
			while(rs.next()){
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setIdentifier(rs.getString(4));
			}
			return user;
		}catch( Exception e ){		
			e.printStackTrace();
			return null;
		}
		
	}
	
	public User verify( Account ac ){
		
		if( ac == null ) return null;
		
		ArrayList<User> userList = findUserByAccount(ac);
		if( userList.isEmpty() ) return null;
		
		for( int i = 0; i < userList.size(); ++i ){
			User user = userList.get(i);
			if( user.getPassword().equals( ac.getPassword() ) ){
				return user;		
			}
		}
		
		return null;
	}
	
	public User findUserByIdentifier( Account ac ){
		
		if( ac == null ) return null;
		
		ArrayList<User> userList = findUserByAccount(ac);
		if( userList.isEmpty() ) return null;
		
		for( int i = 0; i < userList.size(); ++i ){
			User user = userList.get(i);
			if( user.getPassword().equals( ac.getPassword() ) 
				&& user.getIdentifier().equals( ac.getIdentifier() ) ){
				return user;		
			}

		}	
		return null;
	}
	
	public User findUserByName( Account ac ){
		
		if( ac == null ) return null;
		
		ArrayList<User> userList = findUserByAccount(ac);
		if( userList.isEmpty() ) return null;
		
		for( int i = 0; i < userList.size(); ++i ){
			User tmp = userList.get(i);
			if( tmp.getUsername().equals( ac.getUsername() ) ){
				return tmp;		
			}
		}
		
		return null;
	}
	
	public User findUserByPIN( Account ac ){
		
		if( ac == null ) return null;
		
		ArrayList<User> userList = findUserByAccount(ac);
		if( userList.isEmpty() ) return null;
		
		for( int i = 0; i < userList.size(); ++i ){
			User tmp = userList.get(i);
			if( tmp.getPassword().equals( ac.getPassword() ) 
				&& tmp.getIdentifier().equals( ac.getIdentifier() )
				&& tmp.getUsername().equals( ac.getUsername() ) ){
				return tmp;		
			}
		}
		
		return null;
	}
	
	public boolean cpwd(Account ac, String new_pwd, int op_id) {
		// TODO Auto-generated method stub

		try {
			User user = this.findUserByIdentifier(ac);
			if( user == null ) return false;
			
			AccountDAO acDao = new AccountDAO();
			Account acnt = acDao.findById(ac.getId());
			if( acnt == null || !acnt.getSts() ) return false;
			
			String sql = "update user set password="+new_pwd+" where id="+user.getId();
			stms.execute(sql);
			
	    	 LogDAO logDao = new LogDAO();
	    	 logDao.add(ac, 5, user.getId(), op_id, "");    	 
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}

}
