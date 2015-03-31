package nascimentot.servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import nascimentot.dao.StudentConnect;
import nascimentot.model.Student;
import nascimentot.util.DaoUtil;
import nascimentot.util.ValidateUtil;

import java.sql.*;
import java.util.Date;

/**
 * This Class is responsible to authenticate the user on system.
 *@author Thonny
 *@since 2.0
 *@version 4.0 (30-03-15)
 */

@SuppressWarnings("serial")
public class RegistrationServlet extends HttpServlet {

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

			Student aStudent = new Student();
			StringBuffer errorBuffer = new StringBuffer();
			boolean hasError = false;
			try {
				/**
				 * Validating Student Number Field
				 */
				if(DaoUtil.isEmpty(request.getParameter("StudentNumber"))){
					hasError = true;
					errorBuffer.append("The student number cannot be empty!");

				} 
				else {

					/**
					 * try to convert the student number to integer
					 */
					try{
						int studentNumber = Integer.parseInt(request.getParameter("StudentNumber"));
						if(studentNumber < DaoUtil.MINIMUM_STUDENT_NUMBER || studentNumber > DaoUtil.MAXIMUM_STUDENT_NUMBER){

							errorBuffer.append("The StudentNumber must be between "+ DaoUtil.MINIMUM_STUDENT_NUMBER +" and "+ DaoUtil.MINIMUM_STUDENT_NUMBER);

						}else
							aStudent.setStudentNumber(studentNumber);
					} catch (NumberFormatException nfe){
						hasError = true;
						errorBuffer.append("The Student Number must be a number\n");
					}
				}

				aStudent.setFirstName(request.getParameter("FirstName"));
				aStudent.setLastName(request.getParameter("LastName"));

				/**
				 *  Validating student email field
				 */
				if(DaoUtil.isEmpty(request.getParameter("EmailAddress"))){
					hasError = true;
					errorBuffer.append("The Email Address cannot be empty!\n");

				} else if (ValidateUtil.email(request.getParameter("EmailAddress"))){

					aStudent.setEmail(request.getParameter("EmailAddress"));

				}else {

					hasError = true;
					errorBuffer.append("Email not valid valid\n");

				}
				 
				/**
				 *  Validating student phone number field
				 */
				if(DaoUtil.isEmpty(request.getParameter("PhoneNumber"))){

					hasError = true;
					errorBuffer.append("The Phone Number cannot be empty!\n");

				} else if (ValidateUtil.phone(request.getParameter("PhoneNumber"))){
					aStudent.setPhone(request.getParameter("PhoneNumber"));
				}
				else{
					hasError = true;
					errorBuffer.append("Phone Number not valid\n");
				}

				/**
				 *  Validating student Birth date field
				 */
				if(DaoUtil.isEmpty(request.getParameter("BirthYear")) || DaoUtil.isEmpty(request.getParameter("BirthMonth")) || DaoUtil.isEmpty(request.getParameter("BirthDay"))){
					hasError = true;
					errorBuffer.append("The Date Field cannot be empty");
				}
				else{

					try {
						
						int birthYear = Integer.parseInt(request.getParameter("BirthYear")); 
						int birthMonth = Integer.parseInt(request.getParameter("BirthMonth"));
						int birthDay = Integer.parseInt(request.getParameter("BirthDay"));

						if (ValidateUtil.birthYear(birthYear) && ValidateUtil.birthMonth(birthMonth) && ValidateUtil.birthDay(birthDay)){
							
							Date birthDate = DaoUtil.stringToDate(birthYear, birthMonth, birthDay);
							aStudent.setBirthdate(birthDate);
						}else{
							hasError = true;
							errorBuffer.append("The date has an invalid value");
						}
					}catch (NumberFormatException nfe) {
						hasError = true;
						errorBuffer.append("The date must to be a number");
					} 

				}

				/**
				 *  Validating student password field
				 */
				if(DaoUtil.isEmpty(request.getParameter("Password"))){

					hasError = true;
					errorBuffer.append("The Password cannot be empty!\n");

				}
				else{
					if (ValidateUtil.password(request.getParameter("Password"))){
						if (request.getParameter("Password").equals(request.getParameter("Repassword"))){
							aStudent.setPassword(request.getParameter("Password"));	
						}
						else{
							hasError = true;
							errorBuffer.append("The passwords entered are not the same\n");
						}
					}
					else{

						hasError = true;
						errorBuffer.append("The passwords has an invalid format\n");
					}
				}

				if (hasError) {
					session.setAttribute("errors", errorBuffer.toString());
					session.setAttribute("aStudent", aStudent);
					response.sendRedirect("./registration.jsp");
				}else{

					Student.insert(aStudent);
					/**
					 *  redirect the response to a JSP
					 */
					response.sendRedirect("./login.jsp");
				}
			} catch (Exception e) {
				/**
				 * new code == way better, if I do say so myself
				 * sending errors to the page thru the session
				 */
				hasError = true;
				errorBuffer.append("Please try again.");
				errorBuffer.append(e.getMessage());

				session.setAttribute("errors", errorBuffer.toString());
				response.sendRedirect("./registration.jsp");
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