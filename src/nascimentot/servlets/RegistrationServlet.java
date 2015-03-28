package nascimentot.servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import nascimentot.dao.StudentConnect;
import nascimentot.exception.DuplicateStudentException;
import nascimentot.model.Student;
import nascimentot.util.DaoUtil;
import nascimentot.util.EmailValidator;

import java.sql.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This Class is responsible to authenticate the user on system.
 *@author Thonny
 *@since 1.0
 *@version 2.0 (12-03-15)
 */

@SuppressWarnings("serial")
public class RegistrationServlet extends HttpServlet {

	/** (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		Student aStudent = new Student();

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

		StringBuffer errorBuffer = new StringBuffer();
		EmailValidator emailAddress = new EmailValidator();
		int errors = 0;

		try{
			/**
			 * test if the student number is not empty
			 */
			if(request.getParameter("studentNumber")!=""){

				try{

					if(Integer.parseInt(request.getParameter("studentNumber")) >= DaoUtil.MINIMUM_STUDENT_NUMBER && 
							Integer.parseInt(request.getParameter("studentNumber")) <= DaoUtil.MAXIMUM_STUDENT_NUMBER){
							errorBuffer.append("The student id has to be between 100000000 and 999999999.");
							session.setAttribute("errors", errorBuffer.toString());
							errors ++;
					} else {
						/*test is there is already this student id in the database*/
						if(Student.isExistingLogin(aStudent.getStudentNumber())){
							
							aStudent.setStudentNumber(Integer.parseInt(request.getParameter("studentNumber")));
							System.out.println("Student ID "+aStudent.getStudentNumber()+" successfully validated!");				
						} else {
							errorBuffer.append("There is already a student with the id ");
							session.setAttribute("errors", errorBuffer.toString());
							errors ++;		
						}
					}				
				} catch (NumberFormatException nfe){
					errorBuffer.append("The student id has to be a number.");
					session.setAttribute("errors", errorBuffer.toString());
					errors ++;
				}
			}
			else {

				errorBuffer.append("The student number cannot be empty!");
				session.setAttribute("errors", errorBuffer.toString());
				errors ++;
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