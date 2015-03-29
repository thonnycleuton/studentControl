package nascimentot.servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import nascimentot.dao.StudentConnect;
import nascimentot.exception.StudentNotFoundException;
import nascimentot.model.Student;
import nascimentot.util.DaoUtil;
import nascimentot.util.EmailValidator;

import java.sql.*;
import java.util.Date;

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
			int errors = 0;


			try {

				if(request.getParameter("StudentNumber")!=""){
					/*try convert the student number in a integer*/
					try{

						aStudent.setStudentNumber(Integer.parseInt(request.getParameter("StudentNumber")));

						/*test if the user id is between 100000000 and 999999999*/
						if(aStudent.getStudentNumber() < DaoUtil.MINIMUM_STUDENT_NUMBER || aStudent.getStudentNumber() > DaoUtil.MAXIMUM_STUDENT_NUMBER){

							errorBuffer.append("The StudentNumber must be between 100000000 and 999999999.");
							session.setAttribute("errors", errorBuffer.toString());
							response.sendRedirect("./registration.jsp");

						} else {

							/*test is there is already this student id in the database*/
							if(Student.isExistingLogin(aStudent.getStudentNumber())){
								System.out.println("Student ID "+aStudent.getStudentNumber()+" successfully validated!");						
							} else {
									errorBuffer.append("There is already a student with the id ");
									session.setAttribute("errors", errorBuffer.toString());
									response.sendRedirect("./registration.jsp");
							}

						}				
					} catch (NumberFormatException nfe){
						errorBuffer.append("The Student Number must be a number");
						session.setAttribute("errors", errorBuffer.toString());
						response.sendRedirect("./registration.jsp");
					}
				} else {

						errorBuffer.append("The student number cannot be empty!");
						session.setAttribute("errors", errorBuffer.toString());
						response.sendRedirect("./registration.jsp");
				}

				aStudent.setFirstName(request.getParameter("FirstName"));
				aStudent.setLastName(request.getParameter("LastName"));
				aStudent.setEmail(request.getParameter("EmailAddress"));
				aStudent.setPhone(request.getParameter("PhoneNumber"));

				Date birthDate = DaoUtil.stringToDate(

						Integer.parseInt(request.getParameter("BirthYear")), 
						Integer.parseInt(request.getParameter("BirthMonth")), 
						Integer.parseInt(request.getParameter("BirthDay"))
						);

				aStudent.setBirthdate(birthDate );
				aStudent.setPassword(request.getParameter("Password"));

				Student.insert(aStudent);

				/**
				 *  redirect the response to a JSP
				 */
				response.sendRedirect("./VerifyCustInfo.jsp");
			} catch (Exception e) {
				/**
				 * new code == way better, if I do say so myself
				 * sending errors to the page thru the session
				 */
				errorBuffer.append("Please try again.");

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

	public StringBuffer appendError(StringBuffer sb, String message){
		sb.append("<h4 style=\"margin-left: 40px;\"> - "+message+"</h4>");
		return sb;
	}

	public StringBuffer generateErrorHeader(StringBuffer sb){
		sb.append("<h3 style=\"margin-left: 30px; margin-top: 40px; color:red;\">Please watch the following errors</h3>");
		return sb;
	}


}