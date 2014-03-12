package titan.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import titan.bean.Account;
import titan.bean.Log;

public class LogDAO extends BaseDAO{
	
	/**
	 * @category Add log by log_type
	 * @param ac Account include information
	 * @param type Log type
	 * @param user_id The ID of User of the Account
	 * @param op_id The ID of the Operator
	 * @param curr String needed here
	 * */
	public void add( Account ac, int type, int user_id, int op_id, String curr ){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		String time = "'"+sdf.format(System.currentTimeMillis())+"'";	
		String content = "";
		switch( type ){
			case 0: content = "Open Account"; break;
			case 1: content = "Save $"+ac.getBalance()+", <br>total $"+curr; break;
			case 2: content = "Withdraw $"+ac.getBalance()+", <br>total $"+curr; break;
			case 3: content = "Check Account"; break;
			case 5: content = "Change Password"; break;
			case 6: content = "Close Account,<br>and Withdraw whole money"; break;			
			case 7: content = "Add Super "+curr+" to Account "+ac.getId(); break;
			default: break;
		}		
		
		String values = type + ", " +time+", '"+content+"', "+op_id+", "+ac.getId()+", "+user_id;
		super.add("log", values);	
	}
	
	public void add_transfer( Account ac, Account target_ac, int user_id, int target_id, int op_id, String curr, String target_curr ){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String time = "'"+sdf.format(System.currentTimeMillis())+"'";	
		String content = "'Transfer out $" + ac.getBalance() + ", <br>total " + curr+"'";	
		String values = "4, "+time+", "+content+", "+op_id+", "+ac.getId()+", "+user_id;
		super.add("log", values);	
		
		content = "'Transfer in $" + target_ac.getBalance() + ", <br>total " + target_curr+"'";	
		values =  "4, "+time+", "+content+", "+op_id+", "+target_ac.getId()+", "+target_id;
		super.add("log", values);	
	}
	
	public JSONArray findByUserId( int id ){
		try {
			String sql = "select * from log where user_id="+id;
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("content",rs.getString(4));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public JSONArray findByOpType( int id,int type ){
		try {
			String sql = "select * from log where user_id="+id+" and op_type="+type;
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("content",rs.getString(4));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public JSONArray findByOpId(int op_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from log where op_id="+op_id;
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			UserDAO userDao = new UserDAO();			
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("account",rs.getString(6));
				json.put("user",userDao.findById( Integer.parseInt(rs.getString(7)) ).getUsername() );
				json.put("content",rs.getString(4));
				json.put("operator",rs.getString(5));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public JSONArray findByDate(int op_id, String date_type, String date) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from log where op_id="+op_id;
			String[] data = date.split("-");
			String year = data[0];
			String month = "", day = "";

			if( date_type.equals("0") ){
				month = data[1];
				day = data[2];		
				sql += " and year(op_time) = ("+year+") and month(op_time) = ("+month+") and day(op_time) = ("+day+")";
			}else if( date_type.equals("1") ){
				month = data[1];
				sql += " and year(op_time) = ("+year+") and month(op_time) = ("+month+")";
			}else if( date_type.equals("2") ){
				int seasonNum = Integer.parseInt(data[1]);	 
				switch( seasonNum ){
					case 1:  month = "1,2,3"; break;
					case 2:  month = "4,5,6"; break;
					case 3:	 month = "7,8,9"; break;
					case 4:	 month = "10,11,12"; break;		
					default: break;
				}
				sql += " and year(op_time) = ("+year+") and month(op_time) in ("+month+")";
			}else if( date_type.equals("3") ){
				sql += " and year(op_time) = ("+year+")";
			}
			
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			UserDAO userDao = new UserDAO();			
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("account",rs.getString(6));
				json.put("user",userDao.findById( Integer.parseInt(rs.getString(7)) ).getUsername() );
				json.put("content",rs.getString(4));
				json.put("operator",rs.getString(5));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public JSONArray findByDeptId(int dept_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from log where op_id in (select id from login where department="+dept_id+")";
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			UserDAO userDao = new UserDAO();			
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("account",rs.getString(6));
				json.put("user",userDao.findById( Integer.parseInt(rs.getString(7)) ).getUsername() );
				json.put("content",rs.getString(4));
				json.put("operator",rs.getString(5));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public JSONArray findByDeptDate(int dept_id, String date_type, String date) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from log where op_id in (select id from login where department="+dept_id+")";
			String[] data = date.split("-");
			String year = data[0];
			String month = "", day = "";
	
			if( date_type.equals("0") ){
				month = data[1];
				day = data[2];		
				sql += " and year(op_time) in ("+year+") and month(op_time) in ("+month+") and day(op_time) in ("+day+")";
			}else if( date_type.equals("1") ){
				month = data[1];
				sql += " and year(op_time) in ("+year+") and month(op_time) in ("+month+")";
			}else if( date_type.equals("2") ){
				int seasonNum = Integer.parseInt(data[1]);	 
				switch( seasonNum ){
					case 1:  month = "1,2,3"; break;
					case 2:  month = "4,5,6"; break;
					case 3:	 month = "7,8,9"; break;
					case 4:	 month = "10,11,12"; break;		
					default: break;				
				}
				sql += " and year(op_time) in ("+year+") and month(op_time) in ("+month+")";
			}else if( date_type.equals("3") ){
				sql += " and year(op_time) in ("+year+")";
			}
			
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			UserDAO userDao = new UserDAO();			
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("account",rs.getString(6));
				json.put("user",userDao.findById( Integer.parseInt(rs.getString(7)) ).getUsername() );
				json.put("content",rs.getString(4));
				json.put("operator",rs.getString(5));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public JSONArray findByBankId(int bank_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from log where op_id in (select id from login where bank="+bank_id+")";
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			UserDAO userDao = new UserDAO();			
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("account",rs.getString(6));
				json.put("user",userDao.findById( Integer.parseInt(rs.getString(7)) ).getUsername() );
				json.put("content",rs.getString(4));
				json.put("operator",rs.getString(5));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public JSONArray findByBankDate(int bank_id, String date_type, String date) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from log where op_id in (select id from login where bank="+bank_id+")";
			String[] data = date.split("-");
			String year = data[0];
			String month = "", day = "";
	
			if( date_type.equals("0") ){
				month = data[1];
				day = data[2];		
				sql += " and year(op_time) in ("+year+") and month(op_time) in ("+month+") and day(op_time) in ("+day+")";
			}else if( date_type.equals("1") ){
				month = data[1];
				sql += " and year(op_time) in ("+year+") and month(op_time) in ("+month+")";
			}else if( date_type.equals("2") ){
				int seasonNum = Integer.parseInt(data[1]);	 
				switch( seasonNum ){
					case 1:  month = "1,2,3"; break;
					case 2:  month = "4,5,6"; break;
					case 3:	 month = "7,8,9"; break;
					case 4:	 month = "10,11,12"; break;		
					default: break;			
				}
				sql += " and year(op_time) in ("+year+") and month(op_time) in ("+month+")";
			}else if( date_type.equals("3") ){
				sql += " and year(op_time) in ("+year+")";
			}
			
			ResultSet rs = stms.executeQuery(sql);
			
			JSONArray result = new JSONArray();
			UserDAO userDao = new UserDAO();			
			
			int num = 1;
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id",num++);
				json.put("account",rs.getString(6));
				json.put("user",userDao.findById( Integer.parseInt(rs.getString(7)) ).getUsername() );
				json.put("content",rs.getString(4));
				json.put("operator",rs.getString(5));
				json.put("time",rs.getObject(3));			
				result.put(json);
			}
					
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
}
