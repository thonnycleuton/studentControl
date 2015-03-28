/**
 * 
 */
package nascimentot.model;

/**
 * This Class store information about Student
 *@author Thonny
 *@since 2.0
 *@version 2.0 (12-03-15)
 */
public class Mark {



	/**
	 * Course Code
	 */
	private String courseCode;
	/**
	 * Course Title
	 */
	private String courseTitle;
	/**
	 * Weigthing of a Class
	 */
	private float weigthing;
	/**
	 * Final Class Mark
	 */
	private int finalMark;
	
	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/**
	 * @return the courseTitle
	 */
	public String getCourseTitle() {
		return courseTitle;
	}
	/**
	 * @param courseTitle the courseTitle to set
	 */
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	/**
	 * @return the weigthing
	 */
	public float getWeigthing() {
		return weigthing;
	}
	/**
	 * @param weigthing the weigthing to set
	 */
	public void setWeigthing(float weigthing) {
		this.weigthing = weigthing;
	}
	/**
	 * @return the finalMark
	 */
	public int getFinalMark() {
		return finalMark;
	}
	/**
	 * @param finalMark the finalMark to set
	 */
	public void setFinalMark(int finalMark) {
		this.finalMark = finalMark;
	}
	/**
	 * Contructor of Mark Class
	 * @param studentNumber
	 * @param courseCode
	 * @param courseTitle
	 * @param weigthing
	 * @param finalMark
	 */
	public Mark(String courseCode, String courseTitle,
			float weigthing, int finalMark) {
		super();
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.weigthing = weigthing;
		this.finalMark = finalMark;
	}
}
