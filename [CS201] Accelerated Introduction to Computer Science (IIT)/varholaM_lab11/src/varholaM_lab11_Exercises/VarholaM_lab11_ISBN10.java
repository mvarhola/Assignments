/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/02/2015
 * 	Program Name: ISBN10 class (Lab 10 Activity)
 */


package varholaM_lab11_Exercises;

public class VarholaM_lab11_ISBN10 {

	private String isbn;
	
	public VarholaM_lab11_ISBN10(String isbn){
		this.isbn = isbn;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public boolean checkFormat(){
		return isbn.matches("[-\\d]{9}[X\\d]");	//Checks for length of 10, where first 9 characters are digit (or -) and last is either X or a digit
	}

	public int generateChecksum(){
		int checksum = 0;
		for (int i = 10; i>0; i--){
			checksum+= i* ((isbn.charAt(10-i)=='X') ? 10 : (isbn.charAt(10-i)=='-') ? 0 : Character.getNumericValue(isbn.charAt(10-i))); //ternary operator returns 10 if char is X, valueof(char) if false
		}
		return checksum;
	}
	
	public boolean isValid(){
		return (checkFormat()&&(generateChecksum()%11 == 0));
	}
	
	public String toString(){
		return getIsbn();
	}
}
