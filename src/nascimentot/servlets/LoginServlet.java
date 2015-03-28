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
public class LoginServlet extends HttpServlet {

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
            String password = new String ();
            try{   
            	/**
            	 *  retrieve data from DB
            	 */
                login = request.getParameter( "Login" );
                password = request.getParameter("password");
                Student aStudent = Student.login(Integer.parseInt(login), password);
                /**
                 *  put the found Student onto the session
                 */
                session.setAttribute("Student", aStudent);
                session.setAttribute("errors", "");
         
                /**
                 *  redirect the response to a JSP
                 */
                response.sendRedirect("./VerifyCustInfo.jsp");
            }
            catch( NumberFormatException nfe){
                /**
                 * new code == way better, if I do say so myself
                 * sending errors to the page thru the session
                 */
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your login id is your student, you entered: " +login+ "<br/>");
                errorBuffer.append("Please try again.</strong>");
                session.setAttribute("login", "");
                
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
            
            /**
             * for the assignment you will have to check if there was a problem
             * with just the password, or login id and password
             * this will require a special method e.g. public static boolean isValidLogin(String arg);
             */
            }catch( StudentNotFoundException nfe){
                /**
                 * new code == way better, if I do say so myself
                 * sending errors to the page thru the session
                 */
            	StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                if(Student.isExistingLogin(Integer.parseInt(login))){
                	session.setAttribute("login", login);
                }
                else{
                  errorBuffer.append("Invalid login id.</strong>");
                  session.setAttribute("login", "");
                }
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
            
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