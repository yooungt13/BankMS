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
import titan.dao.LogDAO;
import titan.dao.UserDAO;

public class CheckPersonalReportServ extends HttpServlet {

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
			 
		     int op_id = Integer.parseInt(req.getParameter("op_id").trim());
		     String date_type = req.getParameter("date_type").trim();
		     String date = req.getParameter("date").trim();
		     
		     Login login = (Login) req.getSession().getAttribute("login");
		        	    	 
		    LogDAO logDao = new LogDAO();
		    	 
		    if( date.equals("") )
		    	out.write( logDao.findByOpId(op_id).toString() ); 
		    else
		    	out.write( logDao.findByDate(op_id, date_type,date).toString() );    	  

	    	out.close();
		}catch(Exception e){
			e.printStackTrace();	
	    }
	}

}
