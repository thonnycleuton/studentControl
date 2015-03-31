/**
 * 
 */
package nascimentot.util;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Provides a set of methods to help the system in code reuse
 *@author Thonny
 *@since 1.0
 *@version 4.0 (30-03-15)
 */
public class DaoUtil {

	/**
	 * Set the smaller value for the student number
	 */
	public static final int MAXIMUM_STUDENT_NUMBER = 999999999;
	/**
	 * Set the greater value for the student number
	 */
	public static final int MINIMUM_STUDENT_NUMBER = 100000000;
	/**
	 * Set the smaller value for the password
	 */
	public static final int MINIMUM_PASSWORD = 6;
	/**
	 * Set the greater value for the password
	 */
	public static final int MAXIMUM_PASSWORD = 12;
	/**
	 * Turn a set of Three numbers into Date object
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDay
	 * @return Date Oject
	 */
	public static Date stringToDate(int birthYear, int birthMonth, int birthDay){

		/**
		 * my calendar is responsible to inform the date when the class require it
		 */
		GregorianCalendar myCalendar = new GregorianCalendar();
		/**
		 * set a date manually which will be used forward by "mybirthDay" and "releaseOfTheWorldWideWeb"
		 */
		myCalendar.set(birthYear,birthMonth,birthDay);
		/**
		 * Treat the field "birthday" before it be send to Database
		 */
		Date birthday = myCalendar.getTime();
		
		return birthday;

	}
	/**
	 * Verify if a string is empty and null
	 * @param field
	 * @return
	 */
	public static boolean isEmpty(String field){

		return (field.isEmpty() && field != null) ? true:false;
	}
}
