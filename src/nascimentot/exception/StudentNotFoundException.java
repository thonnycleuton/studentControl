/**
 * 
 */
package nascimentot.exception;

/**
 * This Class treats exceptions about not found Students in the database
 *@author Thonny
 *@since 1.0
 *@version 1.0 (08-03-15)
 */
@SuppressWarnings("serial")
public class StudentNotFoundException extends Exception{

	/**
	 * 
	 */
	public StudentNotFoundException() {
		super();
	}
	
	public StudentNotFoundException(String message){
		super(message);
	}
}
