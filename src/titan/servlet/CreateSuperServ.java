package titan.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import titan.bean.Account;
import titan.bean.Login;
import titan.bean.User;
import titan.dao.AccountDAO;
import titan.dao.LogDAO;
import titan.dao.UserDAO;

public class CreateSuperServ extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try{
			 HttpSession session = req.getSession();
			 resp.setContentType( "text/json;charset=utf-8"); 
			 resp.setHeader( "Cache-Control", "no-cache"); 
			 resp.setHeader( "Pargma", "no-cache"); 
			 
			// 创建响应输出，通过out将返回值写入response
		     PrintWriter out  = resp.getWriter();
			 
		     int ac_id = Integer.parseInt(req.getParameter("ac_id").trim());
		     String user_name = req.getParameter("user_name").trim();   
		     String identifier = req.getParameter("identifier").trim();   
		     String pwd = req.getParameter("pwd").trim();
		     int user_id = Integer.parseInt(req.getParameter("user_id").trim());
		      	     
		     Login login = (Login) req.getSession().getAttribute("login");
		 	
		     AccountDAO acDao = new AccountDAO();
		     Account ac = acDao.findById(ac_id);  
		     	     
		     if( ac != null ){		
		    	 ac.setUsername(user_name);
		    	 ac.setIdentifier(identifier);
		    	 ac.setPassword(pwd);
		    	 UserDAO userDao = new UserDAO();
			     User user = userDao.findUserByPIN(ac);
			     
			     if( user != null ){			     
			    	 if( ac.getSts() && ac.getType().charAt(0) == '2' ){
				    	 acDao.add_relationship(ac_id, user_id);
				    	 LogDAO logDao = new LogDAO();
				    	 logDao.add(ac, 7, user.getId(), login.getLoginid(), user_id+"");	    		
				    	 out.write("true"); 
			    	 }else 
			    		 out.write("false");		
			     }else{			    	 
			    	 out.write("false");		
			     }
		     }else{
		    	 out.write("false"); 
		     }
		    
	    	out.close();
		}catch(Exception e){
			e.printStackTrace();	
	    }
	}

}
