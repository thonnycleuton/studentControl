/**
 * 
 */
package nascimentot.test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Vector;

import nascimentot.dao.StudentConnect;
import nascimentot.exception.DuplicateStudentException;
import nascimentot.exception.StudentNotFoundException;
import nascimentot.model.Student;
/**
 * This Class instantiates some methods to demonstrate the functionality of the system
 *@author Thonny
 *@since 1.0
 *@version 2.0 (12-03-15)
 */
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * Start a new connection with Database
		 */
        Connection c = StudentConnect.initialize();
        Student.initialize(c);
        /**
         * Define a new format for Dates
         */
        SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        
		/**
		 * my calendar is responsible to inform the date when the class require it
		 */
		GregorianCalendar myCalendar = new GregorianCalendar();
		/**
		 * set a date manually which will be used forward by "mybirthDay" and "releaseOfTheWorldWideWeb"
		 */
		myCalendar.set(1943,9,6);
		/**
		 * Treat the field "birthday" before it be send to Database
		 */
		Date birthday = myCalendar.getTime();
        
		Student aStudent = new Student(100111120, "100111120", "Roger", "Waters", birthday, "waters@gmail.com", "9059221363");
		Vector<Student> students = new Vector<Student>();
		/**
		 * Try to Insert a new student
		 */
		try {
			Student.insert(aStudent);
		} catch (DuplicateStudentException e) {
			e.printStackTrace();
		}
		/**
		 * Try to get all students from DB
		 */
		try {
			students = Student.getAllStudents();
		} catch (StudentNotFoundException e) {
			e.printStackTrace();
			System.out.println("catch");
		}
		/**
		 * Update a Student
		 */
/*		try {
			aStudent.setFirstName("Peter");
			Student.upDate(aStudent);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		/**
		 * Try to delete a Student from DB
		 */
/*		try {
			Student.delete(aStudent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/**	
		 * Show all students stored in the DB
		 */
		System.out.println("\nAll students\n----------------------------------------");
		for(Student std : students){
			
			System.out.println("Student Number : "+ std.getStudentNumber());
			System.out.println("\nName : "+ std.getFirstName()+" "+std.getLastName());
			System.out.println("Email : "+ std.getEmail());
			System.out.println("Birthday : "+ std.getBirthdate());
			System.out.println("Avarage: " + std.calculateGPA());
			System.out.println("\n----------------------------------------");
		}
	}
}
