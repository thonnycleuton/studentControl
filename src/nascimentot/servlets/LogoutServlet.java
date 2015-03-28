package nascimentot.servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import nascimentot.dao.StudentConnect;
import nascimentot.exception.StudentNotFoundException;
import nascimentot.model.Student;

import java.sql.*;

/**
 * This Class is responsible to authenticate the user on system.
 *@author Thonny
 *@since 1.0
 *@version 2.0 (12-03-15)
 */

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

	/** (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException
	{

		try
		{ 
			/**
			 *  connect to database
			 */
			Connection c = StudentConnect.initialize();
			Student.initialize(c);
			HttpSession session = request.getSession();
			session.invalidate();
            response.sendRedirect("./login.jsp");

		}catch (Exception e) //not connected
		{
			System.out.println(e);
			String line1="<h2>A network error has occurred!</h2>";
			String line2="<p>Please notify your system " +
					"administrator and check log. "+e.toString()+"</p>";
			formatErrorPage(line1, line2,response);
		}
	}
	/**
	 * Print a personalized page for errors
	 * @param first
	 * @param second
	 * @param response
	 * @throws IOException
	 */
	public void formatErrorPage( String first, String second, HttpServletResponse response) throws IOException{

		PrintWriter output = response.getWriter();
		response.setContentType( "text/html" );
		output.println(first);
		output.println(second);
		output.close();
	}
}