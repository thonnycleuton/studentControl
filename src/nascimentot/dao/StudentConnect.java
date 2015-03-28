/**
 * 
 */
package nascimentot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nascimentot.model.Student;

/**
 * This Class creates a model of Student.
 *@author Thonny
 *@since 1.0
 *@version 1.0 (08-03-15)
 */

//This Class makes transations with the Database
public class StudentConnect {

	static Student aStudent;
	
	/**
	 * set informations about Database
	 */
	static String url = "jdbc:postgresql://127.0.0.1:5432/intn6203_db";
	/**
	 * starting a new connection
	 */
	static Connection aConnection;
	/**
	 * getting a new user
	 */
	static String user = "intn6203_admin";
	/**
	 * getting a password
	 */
	static String password = "password";
	
	/**
	 *  establish the database connection
	 * @return a connection
	 */
	public static Connection initialize(){
		
		try{ 	
			/**
			 *  load the jdbc - odbc Driver for PostGreSQL
			 */
			Class.forName("org.postgresql.Driver");
			/**
			 *  create connection instance
			 */
	    	aConnection = DriverManager.getConnection(url, user, password);
	 	}
		catch (ClassNotFoundException e)
		{
			System.out.println(e);
		}
		catch (SQLException e){
			System.out.println(e); 
		}
		return aConnection;
		
	}

	/**
	 *  close the database connection
	 */
	public static void terminate(){
		try{
    		aConnection.close();
		}
		catch (SQLException e){ 
			System.out.println(e);
		}
	}
}
