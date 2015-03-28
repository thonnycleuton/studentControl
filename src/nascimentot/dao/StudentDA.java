/**
 * 
 */
package nascimentot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import nascimentot.exception.DuplicateStudentException;
import nascimentot.exception.StudentNotFoundException;
import nascimentot.model.Mark;
import nascimentot.model.Student;

/**
 * This Class creates a model of Student.
 *@author Thonny
 *@since 1.0
 *@version 2.0 (12-03-15)
 */
public class StudentDA {

	/**
	 * 
	 */
	static Student aStudent = null;
	static Connection aConnection;
	static Statement aStatement;
	static String name;
	static int studentNumber;
	static String password;
	static String firstName;
	static String lastName;
	static Date birthdate;
	static String email;
	static String phone;
	
	static Mark mark;
	static String courseCode;
	static String courseTitle;
	static float GPAWeighting;
	static int result;
	
	/**
	 * Constructor Default
	 */
	public StudentDA() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method authenticates the Student on System
	 */
	public static Student login(int aStudentNumber, String aPassword)throws StudentNotFoundException {

		if(Student.isExistingLogin(aStudentNumber) && (aStudent.getPassword().equals(aPassword))){
			try {
				aStudent = Student.find(aStudentNumber);
			} 
			catch (Exception e) {
				System.out.println(e);
			}
		}
		else{
			throw (new StudentNotFoundException("invalid password"));
		}

		return aStudent;
	}

	/**
	 * Responsible for start a connection with database 
	 */
	public static void intialize(Connection c) {

		try{
			aConnection=c;
			aStatement=aConnection.createStatement();
		}
		catch (SQLException e){
			System.out.println(e);
		}
	}

	// close the database connection
	public static void terminate(){
		try{ 	// close the statement
			aStatement.close();
		}
		catch (SQLException e)
		{ System.out.println(e);	}
	}

	/**
	 * Try to find a student in the database
	 * @param aStudentNumber
	 * @return
	 * @throws StudentNotFoundException
	 */
	public static Student find(int aStudentNumber) throws StudentNotFoundException{

		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT \"StudentNumber\", \"Password\", \"FirstName\", \"LastName\", \"BirthDate\", \"EmailAddress\", \"PhoneNumber\""
				+ "FROM \"Students\" "
				+ "WHERE \"StudentNumber\" = '"+ aStudentNumber +"';";

		/**
		 * execute the SQL query statement
		 */
		try{
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			/**
			 *  next method sets cursor & returns true if there is data
			 */
			boolean gotIt = rs.next();

			if (gotIt){	// extract the data
				studentNumber = rs.getInt("studentnumber");
				password = rs.getString("password");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				birthdate = rs.getDate("birthDate");
				email = rs.getString("emailaddress");
				phone = rs.getString("phonenumber");

				/**
				 * adding data to variable aStudent
				 */
				aStudent = new Student(studentNumber, password, firstName, lastName, birthdate, email, phone);
			}
			else{	// nothing was retrieved
				throw (new StudentNotFoundException("not found "));
			}
			rs.close();
		}
		catch (SQLException e){
			System.out.println(e);
		}
		return aStudent;
	}

	//Check if there is already a student number in database 
	public static boolean isExistingLogin(int login) {

		boolean exists = false;                  
		try {
			Student.find(login);
			exists = true;
		} catch (StudentNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return exists;
	}

	/**
	 * Find all students stored in the database and retrieve them
	 * @return an object of Student type
	 */
	public static Vector<Student> getAllStudents(){

		Vector<Student> students = new Vector<Student>();
		
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT \"StudentNumber\", \"Password\", \"FirstName\", \"LastName\", \"BirthDate\", \"EmailAddress\", \"PhoneNumber\""
				+ "FROM \"Students\" ";

		// execute the SQL query statement
		try{
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			// next method sets cursor & returns true if there is data
			boolean gotIt = rs.next();

			do{// extract the data
				
				studentNumber = rs.getInt("StudentNumber");
				firstName = rs.getString("FirstName");
				lastName = rs.getString("LastName");
				email = rs.getString("EmailAddress");
				birthdate = rs.getDate("BirthDate");
				phone = rs.getString("PhoneNumber");
				password = rs.getString("Password");
				
				Vector<Mark> marks = getTranscript(studentNumber);
				
				aStudent = new Student(studentNumber, password, firstName, lastName, birthdate, email, phone, marks);

				students.add(aStudent);
				
			}while(rs.next());
		}
		catch (SQLException e){
			System.out.println(e);
		}
		return students;
	}
	/**
	 * Retrieves all marks from student 
	 * @param studentNumber
	 * @return set of Student Marks
	 */
	public static Vector<Mark> getTranscript(int studentNumber){
		
		Vector<Mark> marks = null;

		/**
		 * Define the SQL query statement using the phone number key
		 */
		String sqlQuery = "SELECT marks.coursecode, \"CourseTitle\", \"GPAWeighting\", \"result\" "
				+ "FROM marks,\"Courses\" "
				+ "WHERE \"studentnumber\" = " + studentNumber
				+ "AND marks.coursecode = \"Courses\".\"CourseCode\";";

		/**
		 *  execute the SQL query statement
		 */
		try{
			Statement localStatement = aConnection.createStatement();
            ResultSet rs = localStatement.executeQuery(sqlQuery);
            marks = new Vector<Mark>();
			// next method sets cursor & returns true if there is data
			while(rs.next())
			{// extract the data
				courseCode = rs.getString("CourseCode");
				courseTitle = rs.getString("CourseTitle");
				GPAWeighting = rs.getFloat("GPAWeighting");
				result = rs.getInt("Result");
				
				mark = new Mark(courseCode, courseTitle, GPAWeighting, result);
				marks.add(mark);
				
			}
		}
		catch (SQLException e){
			System.out.println(e);
		}
		
		return marks;
	}
	
	
	/**
	 * Insert a new student in the database after to check if there is this student already
	 * @param aStudent
	 * @throws DuplicateStudentException
	 */
	public static void insert(Student aStudent) throws DuplicateStudentException {

		// define the SQL query statement using the phone number key
		String sqlQuery = "INSERT INTO \"Students\"(\"StudentNumber\", \"Password\", \"FirstName\", \"LastName\", \"BirthDate\", \"EmailAddress\", \"PhoneNumber\")"
				+ "VALUES (" 
				+ aStudent.getStudentNumber() + ", " 
				+ aStudent.getPassword() + ", '" 
				+ aStudent.getFirstName() + "', '"
				+ aStudent.getLastName() + "', '"
				+ aStudent.getBirthdate() + "', '"
				+ aStudent.getEmail() + "', '"
				+ aStudent.getPhone() + "');";

		//check if there is some students in the database with the same Student Number
		if (Student.isExistingLogin(aStudent.getStudentNumber()) != true){

			try {
				aStatement.executeQuery(sqlQuery);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		else{
			throw (new DuplicateStudentException("This student exists already in Database"));
		}
	}
	
	/**
	 * Update fields in the database
	 * @param aStudent
	 * @throws StudentNotFoundException
	 */
	public static void update(Student aStudent) throws StudentNotFoundException {

		// define the SQL query statement using the phone number key

		String sqlQuery = "UPDATE \"Students\" SET "
				+ "\"StudentNumber\" = " + aStudent.getStudentNumber() + ","
				+ "\"Password\" = " + aStudent.getPassword() + ", " 
				+ "\"FirstName\" = '"+ aStudent.getFirstName() + "', "
				+ "\"LastName\" = '"+ aStudent.getLastName() + "', "
				+ "\"BirthDate\" = '"+ aStudent.getBirthdate() + "', "
				+ "\"EmailAddress\" = '"+ aStudent.getEmail() + "', "
				+ "\"PhoneNumber\" = '"+ aStudent.getPhone() + "' "
				+ "WHERE \"StudentNumber\" = " + aStudent.getStudentNumber() + ";" ;

		//Check if there is a Student registered already
		if (Student.isExistingLogin(aStudent.getStudentNumber()) == true){

			try {
				aStatement.executeQuery(sqlQuery);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		else{
			throw (new StudentNotFoundException ());
		}

	}

	/**
	 * After to check that the Student is registered, this method erase it from database
	 * @param aStudent
	 * @throws StudentNotFoundException
	 */
	public static void delete(Student aStudent) throws StudentNotFoundException{

		// define the SQL query statement using the phone number key
		String sqlQuery = "DELETE FROM \"Students\" "
				+ "WHERE \"StudentNumber\" = '"+ aStudent.getStudentNumber() +"';";

		if (Student.isExistingLogin(aStudent.getStudentNumber()) == true){

			try {
				aStatement.executeQuery(sqlQuery);
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		else{
			throw (new StudentNotFoundException ());
		}
	}
}
