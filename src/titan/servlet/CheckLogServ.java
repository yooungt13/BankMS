package titan.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import titan.bean.Account;
import titan.bean.User;
import titan.dao.AccountDAO;
import titan.dao.LogDAO;
import titan.dao.UserDAO;

public class CheckLogServ extends HttpServlet {

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
		     //String identifier = req.getParameter("identifier").trim();   
		     String pwd = req.getParameter("pwd").trim();
		     int type = Integer.parseInt(req.getParameter("type").trim());
		       
		     Account ac = new Account();     
		     ac.setId(ac_id);
		     ac.setPassword(pwd);
		     //ac.setIdentifier(identifier);

		     UserDAO userDao = new UserDAO();		     
		     User user = userDao.verify(ac);
		     
		     if(  user != null ){		    	 
		    	 LogDAO logDao = new LogDAO();
		    	 
		    	 if( type == 0 )
		    		 out.write( logDao.findByUserId(user.getId()).toString() ); 
		    	 else
		    		 out.write( logDao.findByOpType(user.getId(), type).toString() );    	  
		    	
		     }else{
		    	 out.write("false"); 
		     }
		    
	    	out.close();
		}catch(Exception e){
			e.printStackTrace();	
	    }
	}

}
