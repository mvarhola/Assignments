/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/02/2015
 * 	Program Name: ISBN13 class (Lab 10 Activity)
 */


package varholaM_lab11_Exercises;

public class VarholaM_lab11_ISBN13 extends VarholaM_lab11_ISBN10 {

	private String prefix;
	
	public VarholaM_lab11_ISBN13(String isbn){
		super(isbn.substring(3, isbn.length()));
		prefix = isbn.substring(0, 3);
		setIsbn(prefix+getIsbn());
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean checkFormat(){
		return getIsbn().matches("[\\d]{12}[X\\d]");	//Checks for length of 13, where first 12 characters are digit and last is either X or a digit
	}

	public int generateChecksum(){
		int checksum = 0; 
		for (int i = 0; i<12; i++){
			checksum+= ((i%2 == 0) ? 1 : 3)  * ((getIsbn().charAt(i)=='X') ? 10 : Character.getNumericValue(getIsbn().charAt(i))); //ternary operator returns 10 if char is X, valueof(char) if false
		}
		return checksum;
	}
	
	public boolean isValid(){
		return (checkFormat() && ((10-generateChecksum()%10)%10 == Character.getNumericValue(getIsbn().charAt(getIsbn().length()-1))));
	}
	
	
	
}
