package nascimentot.exception;

/**
 * This Class treats exceptions when is attempted to insert a Student that is already in the Database
 *@author Thonny
 *@since 3.0
 *@version 3.0 (25-03-15)
 */
@SuppressWarnings("serial")
public class DuplicateStudentException extends Exception {
	public DuplicateStudentException(){
		super();
	}

	public DuplicateStudentException(String message){
		super(message);
	}
}
