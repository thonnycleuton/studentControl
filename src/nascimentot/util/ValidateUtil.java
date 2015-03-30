/**
 * 
 */
package nascimentot.util;

/**
 * @author Thonny
 *
 */
public class ValidateUtil {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_PATTERN = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d";
	private static final String PASSWORD_PATTERN = "^.{5,11}$";
	
	public static boolean email(String email){

		return email.matches(EMAIL_PATTERN);
		
	}
	
	public static boolean phone(String phone){

		return phone.matches(PHONE_PATTERN);
	}

	public static boolean password(String password) {
		
		return password.matches(PASSWORD_PATTERN);
	}
	
	public static boolean birthYear(int year){
		
		return (year >= 1900 && year <= 2000) ? true:false;
	}

	public static boolean birthMonth (int month){
		
		return (month >= 1 && month <= 12) ? true:false;
	}

	public static boolean birthDay(int day){
		
		return (day >= 1 && day <= 31) ? true:false;
	}
}
