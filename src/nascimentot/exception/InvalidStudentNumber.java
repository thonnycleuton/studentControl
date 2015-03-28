package nascimentot.exception;

/**
 * This Class treats exceptions when is attempted to insert a Student that is already in the Database
 *@author Thonny
 *@since 1.0
 *@version 2.0 (12-03-15)
 */
@SuppressWarnings("serial")
public class InvalidStudentNumber extends Exception {
	public InvalidStudentNumber()
	{ super();}
	
	public InvalidStudentNumber(String message)
	{ super(message);}
}
