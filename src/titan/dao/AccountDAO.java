package titan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import titan.bean.Account;
import titan.bean.Login;
import titan.bean.User;

public class AccountDAO extends BaseDAO{
	
	private UserDAO userDao = new UserDAO();
	private LogDAO logDao = new LogDAO();

	public int add(Account ac, int op_id) {
		// TODO Auto-generated method stub
		try{
			String ac_values =  "'" + ac.getType() + "', " +
									  ac.getBalance() + ", " +
									  ac.getSts();
			String user_values = "'" + ac.getUsername() + "', '" + ac.getPassword() + "', '" + ac.getIdentifier() +"'";
			
			int user_id = super.add("user", user_values);
			int ac_id = super.add("account",ac_values);
			this.add_relationship(ac_id, user_id);
			
			ac.setId(ac_id);
			logDao.add(ac, 0, user_id, op_id, "");
			
			return ac_id;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public void add_relationship( int ac_id, int user_id ){
		try {
			String sql = "insert into relationship values(" + ac_id + ", " + user_id + ")";
			stms.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public boolean delete(Account ac, int op_id) {
		// TODO Auto-generated method stub
		try {
			User user = userDao.findUserByIdentifier(ac);
			if( user == null ) return false;
			
			Account acnt = this.findById(ac.getId());
			if( acnt == null || !acnt.getSts() ) return false;
			
			String sql = "update account set sts=0 where id=" + acnt.getId();
			stms.execute(sql);
			logDao.add(acnt, 6, user.getId(), op_id, "");    				
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	
	public String save(Account ac) {
		// TODO Auto-generated method stub
		try{		
			Account acnt = this.findById(ac.getId());
			if( acnt == null || !acnt.getSts() ) return "false";
			
			Float balance = ac.getBalance() + acnt.getBalance();
			
			super.update("account", ac.getId(), balance);			
			return balance+"";
		}catch(Exception e){
			e.printStackTrace();
			return "false";
		}
	}
	
	public String withdraw(Account ac) {
		// TODO Auto-generated method stub
		try{		
			Account acnt = this.findById(ac.getId());
			
			if( acnt == null || !acnt.getSts() ) return "false";
			Float balance = acnt.getBalance() - ac.getBalance();
			
			switch(  acnt.getType().charAt(0) ){
				case '0':
					if( balance < 0 ) return "false";
					break;
				case '1':
					if( balance < -100000 ) return "false";
					break;
				case '2':
					if( balance < 10000 ) return "false";
					break;		
			}	
			
			super.update("account", ac.getId(), balance);
			return balance+"";
		}catch(Exception e){
			e.printStackTrace();
			return "false";
		}
	}

	public boolean transfer(Account ac, Account target_ac, int op_id){
		User curr = userDao.findUserByPIN(ac);
		User target = userDao.findUserByName(target_ac);
		
		if( curr == null || target == null ) return false;
		Account legalAct = this.findById(ac.getId());
		Account legalTargetAct = this.findById(target_ac.getId());
		
		if( legalAct == null || legalTargetAct == null || !legalAct.getSts() || !legalTargetAct.getSts() ) return false;
		
		switch(  legalAct.getType().charAt(0) ){
			case '0':
				// 普通用户只能在自己账户之间转账，如果账户身份证不一致不与转账
				if( curr.getId() != target.getId() )  return false;
				// 个人用户与企业用户不可以互相转账
				if( legalTargetAct.getType().charAt(0) == '2' )  return false;	
				break;
			case '1':
				// 个人用户与企业用户不可以互相转账
				if( legalTargetAct.getType().charAt(0) == '2' )  return false;	
				break;
			case '2':
				if( legalTargetAct.getType().charAt(0) == '0'  || legalTargetAct.getType().charAt(0) == '1' )  return false;	
				break;		
		}

		String savings = this.save(target_ac);
		String withdrawals = this.withdraw(ac);
		if( !savings.equals("false") && !withdrawals.equals("false")  ) {
	    	logDao.add_transfer(ac, target_ac, curr.getId(), target.getId(), op_id, withdrawals, savings);
			return true;
		}
		return false;			
	}
	
	public Account findById( int id ){
		
		Account ac = new Account();
		try {
			ResultSet rs = super.findById("account",id);
			while(rs.next()){
				ac.setId(rs.getInt(1));
				ac.setType(rs.getString(2));
				ac.setBalance(rs.getFloat(3));	
				ac.setSts(rs.getBoolean(4));
			}					
			
			return ac;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String check(Account ac) {
		// TODO Auto-generated method stub
		Account acnt = this.findById(ac.getId());
		
		if( acnt == null || !acnt.getSts() ) return "false";
		return acnt.getBalance()+"";
	}
		
}
