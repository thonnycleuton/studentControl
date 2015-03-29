/**
 * 
 */
package nascimentot.util;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Thonny
 *
 */
public class DaoUtil {

	/**
	 * 
	 */

	public static final int MAXIMUM_STUDENT_NUMBER = 999999999;
	public static final int MINIMUM_STUDENT_NUMBER = 100000000;
	public static final int MINIMUM_PASSWORD = 6;
	public static final int MAXIMUM_PASSWORD = 12;
	
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
}
