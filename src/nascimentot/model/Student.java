/**
 * 
 */
package nascimentot.model;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.Vector;

import nascimentot.dao.StudentDA;
import nascimentot.exception.DuplicateStudentException;
import nascimentot.exception.StudentNotFoundException;

/**
 * This Class store information about Student
 *@author Thonny
 *@since 1.0
 *@version 2.0 (12-03-15)
 */

public class Student {

	/**
	 * number of identification from student
	 */
	private int studentNumber;
	/**
	 * key to control access to system
	 */
	private String password;
	/**
	 * First Name of Student
	 */
	private String firstName;
	/**
	 * Last Name of Student
	 */
	private String lastName;
	/**
	 * Birthday of the Student
	 */
	private Date birthdate;
	/**
	 * Email from student
	 */
	private String email;
	/**
	 * Telephone from Student
	 */
	private String phone;
	/**
	 * Set of Marks from Student
	 */
	private Vector <Mark> marks;
	
	/**
	 * @return the studentNumber
	 */
	public int getStudentNumber() {
		return studentNumber;
	}
	/**
	 * @param studentNumber the studentNumber to set
	 */
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate the birthday to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the marks
	 */
	public Vector<Mark> getMarks() {
		return marks;
	}
	/**
	 * @param marks the marks to set
	 */
	public void setMarks(Vector<Mark> marks) {
		this.marks = marks;
	}	

	/** 
	 * @description Constructor that receives 8 parameters 
	 * @param studentNumber
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param email
	 * @param phone
	 * @param marks Vector of marks
	 */
	public Student(int studentNumber, String password, String firstName,
			String lastName, Date birthdate, String email, String phone,
			Vector<Mark> marks) {

		this.setStudentNumber(studentNumber);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthdate(birthdate);
		this.setEmail(email);
		this.setPhone(phone);
		this.setMarks(marks);

	}
	/**
	 * Constructor that receives 8 parameters 
	 * @param studentNumber
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 */
	public Student(int studentNumber, String password, String firstName,
			String lastName, Date birthdate, String email, String phone) {
		this(studentNumber, password, firstName, lastName, birthdate, email, phone, null);
	}
	public Student() {
		
	}
	/**
	 * Initiates the connection with DB
	 * @param c 
	 */
	public static void initialize(Connection c) {
		StudentDA.intialize(c);
	}
	/**
	 * Try to find a Student from a given student number 
	 * @param id
	 * @return a StudentDA object
	 * @throws StudentNotFoundException
	 */
	public static Student find(int id) throws StudentNotFoundException {
		return StudentDA.find(id);
	}

	/**
	 * Check if there is a Student registered already
	 * @param login
	 * @return a boolean
	 */
	public static boolean isExistingLogin(int login){
		return StudentDA.isExistingLogin(login);
	}

	/**
	 * close the connection with DB
	 */
	public static void terminate(){
		StudentDA.terminate();
	}	
	/**
	 * Authenticates the Student
	 * @param login
	 * @param password
	 * @return a StudentDA object
	 * @throws StudentNotFoundException
	 */
	public static Student login(int login, String password) throws StudentNotFoundException {
		return StudentDA.login(login, password);
	}
	/**
	 * Calculates the GPA of a student
	 * @return GPA
	 */
	public double calculateGPA(){

		double gpa = 0, sum = 0, sumWeigthing = 0;

		setMarks(getTranscript());

		for (Mark mark : getMarks()) {
			sum = sum + mark.getFinalMark() * mark.getWeigthing();
			sumWeigthing = sumWeigthing + mark.getWeigthing();
		}

		gpa = sum/sumWeigthing;
		/**
		 * Converting the Pondered Average to  Durham College’s 5.0 point GPA system
		 */
		if(gpa >= 90){
			gpa = 5.0;
		}else if(gpa >= 85){
			gpa = 4.5;
		}else if(gpa >= 80){
			gpa = 4.0;
		}else if(gpa >= 75){
			gpa = 3.5;
		}else if(gpa >= 70){
			gpa = 3.0;
		}else if(gpa >= 65){
			gpa = 2.5;
		}else if(gpa >= 60){
			gpa = 2.0;
		}else if(gpa >= 55){
			gpa = 1.5;
		}else if(gpa >= 50){
			gpa = 1.0;
		}else{
			gpa = 0;
		}

		return gpa;
	}

	/**
	 * Insert a new Student in the DB
	 * @param aStudent. Object of Student type
	 * @throws DuplicateStudentException
	 */
	public static void insert(Student aStudent) throws DuplicateStudentException{
		StudentDA.insert(aStudent);
	}
	/**
	 * Delete a Student from DB
	 * @param aStudent. Object of Student type
	 * @throws StudentNotFoundException
	 */
	public static void delete(Student aStudent) throws StudentNotFoundException{
		StudentDA.delete(aStudent);
	}
	/**
	 * Try to find a student and to change its values
	 * @throws StudentNotFoundException
	 */
	public static void upDate(Student aStudent) throws StudentNotFoundException {
		StudentDA.update(aStudent);
	}
	/**
	 * Find all students stored in the database and retrieve them
	 * @return an object of Student type
	 * @throws StudentNotFoundException
	 */
	public static Vector<Student> getAllStudents() throws StudentNotFoundException{
		return StudentDA.getAllStudents();
	}
	/**
	 * Get the collection of marks that belong to a student
	 * @return Vector of Mark objects
	 */
	public Vector<Mark> getTranscript() {
		return StudentDA.getTranscript(this.getStudentNumber());
	}
	/**
	 * Get the collection of marks that belong to a student in HTML format
	 * @return HTML Table of String type
	 */
	@SuppressWarnings("null")
	public String getHTMLTranscript(){

		String table;
		setMarks(getTranscript());

		if (getMarks().size() > 0){
			table = "<table class=\"table-style-three\"><thead><tr>"
					+ "<th>Course Code</th>"
					+ "<th>Course Title</th>"
					+ "<th>Final Mark</th>"
					+ "<th>Weighting</th></tr></thead>\n";
			String tr = "<tbody><th><td>";

			for(Mark mark : getMarks()){
				tr = "<tr><td>" + mark.getCourseCode() 
						+ "<td>" + mark.getCourseTitle() 
						+ "<td>" + mark.getFinalMark() 
						+ "<td>" + mark.getWeigthing() 
						+ "</td></tr></tbody>\n";
				table = table + tr;
			}
			table = table + "<tfoot><tr><td>GPA</td><td></td><td></td><td>" + calculateGPA() + "</td></tr></tfoot></table>";
		}else{
			table = "<div class=\"table\">There are no marks on record for this student</div>";
		}
		
		return table;
	}
}