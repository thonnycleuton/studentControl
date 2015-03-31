package nascimentot.servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import nascimentot.dao.StudentConnect;
import nascimentot.exception.StudentNotFoundException;
import nascimentot.model.Student;
import nascimentot.util.DaoUtil;

import java.sql.*;

/**
 * This Class is responsible to authenticate the user on system.
 *@author Thonny
 *@since 3.0
 *@version 2.0 (27-03-15)
 */

@SuppressWarnings("serial")
public class UpdatePasswordServlet extends HttpServlet {

	/** (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		try{ 
			/**
			 *  connect to database
			 */
			Connection c = StudentConnect.initialize();
			/**
			 * Initialize a connection for a new student
			 */
			Student.initialize(c);
			/**
			 * getting a new session
			 */
			HttpSession session = request.getSession(true);
			/**
			 * variable that will store the login
			 */
			String login = new String();
			/**
			 * storing the password
			 */
			Student aStudent = new Student();
			StringBuffer errorBuffer = new StringBuffer();
			int errors = 0;

			try{   
				/**
				 *  retrieve data from DB
				 */
				aStudent = (Student) session.getAttribute("Student");
				if(request.getParameter("actualPassword").equals(aStudent.getPassword())){

					if(request.getParameter("password") != "" && request.getParameter("repassword") != ""){

						/**
						 * test if the password and confirm password match
						 */
						if(request.getParameter("password").equals(request.getParameter("repassword"))){

							/*Test how long is the password, it is supposed to be between 6 and 12, both inclusive*/
							if(request.getParameter("password").toString().length()>= DaoUtil.MINIMUM_PASSWORD && request.getParameter("password").toString().length()<=DaoUtil.MAXIMUM_PASSWORD){

								aStudent.setPassword(request.getParameter("password"));
								Student.upDate(aStudent);
								System.out.println("Password successfully validated.");

								session.setAttribute("errors", errorBuffer.toString());
								response.sendRedirect("./login.jsp");

							} else {
								String error = "The password has to be at least 6 characters but no longer than 12.";
								if(errors == 0){
									errors++;
									errorBuffer.append(error);
									session.setAttribute("errors", errorBuffer.toString());
									response.sendRedirect("./passwordChange.jsp");
								} else {
									errorBuffer.append(error);
									session.setAttribute("errors", errorBuffer.toString());
									response.sendRedirect("./passwordChange.jsp");
									errors++;
								}
							}					
						} else {
							String error = "The password and confirm password have to be the same!";
							if(errors == 0){
								errorBuffer.append(error);
								session.setAttribute("errors", errorBuffer.toString());
								response.sendRedirect("./passwordChange.jsp");
								errors++;
							} else {
								errorBuffer.append(error);
								session.setAttribute("errors", errorBuffer.toString());
								response.sendRedirect("./passwordChange.jsp");
								errors++;
							}
						}				
					} else {
						String error = "The password and confirm password cannot be empty!";
						if(errors == 0){
							errorBuffer.append(error);
							session.setAttribute("errors", errorBuffer.toString());
							response.sendRedirect("./passwordChange.jsp");
							errors++;
						} else {
							errorBuffer.append(error);
							session.setAttribute("errors", errorBuffer.toString());
							response.sendRedirect("./passwordChange.jsp");
							errors++;
						}	
					}
				}else{
					String error = "Actual Password is incorrect!";
					errorBuffer.append(error);
					session.setAttribute("errors", errorBuffer.toString());
					response.sendRedirect("./passwordChange.jsp");
					errors++;

				}
			}
			catch( NumberFormatException nfe){
				/**
				 * new code == way better, if I do say so myself
				 * sending errors to the page thru the session
				 */
				errorBuffer.append("<strong>Your login id is your student, you entered: " +login+ "<br/>");
				errorBuffer.append("Please try again.</strong>");
				session.setAttribute("login", "");

				session.setAttribute("errors", errorBuffer.toString());
				response.sendRedirect("./passwordChange.jsp");

				/**
				 * for the assignment you will have to check if there was a problem
				 * with just the password, or login id and password
				 * this will require a special method e.g. public static boolean isValidLogin(String arg);
				 */
			}
		}
		catch (Exception e) 
		/**
		 * Returns an exception in case of it doesn't get a connection
		 */
		{
			System.out.println(e);
			String line1="<h2>A network error has occurred!</h2>";
			String line2="<p>Please notify your system " +
					"administrator and check log. "+e.toString()+"</p>";
			formatErrorPage(line1, line2,response);
		}
	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		doPost(request, response);
	}

	public void formatErrorPage( String first, String second,
			HttpServletResponse response) throws IOException
	{
		PrintWriter output = response.getWriter();
		response.setContentType( "text/html" );
		output.println(first);
		output.println(second);
		output.close();
	}
}