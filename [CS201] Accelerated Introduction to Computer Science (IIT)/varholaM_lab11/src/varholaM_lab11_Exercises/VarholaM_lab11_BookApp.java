/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/12/2015
 * 	Program Name: BookApp (Lab 10 Activity)
 */


package varholaM_lab11_Exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class VarholaM_lab11_BookApp {

	public static void main(String[] args){
		
		VarholaM_lab11_GenericList<VarholaM_lab11_Book> list = new VarholaM_lab11_GenericList<VarholaM_lab11_Book>();
		File file = new File("bks13b.txt");
		
		Scanner x;
		try {
			x = new Scanner(file);
			PrintWriter writer = new PrintWriter("VarholaM_lab11_validResults.txt", "UTF-8");

			while (x.hasNextLine()){
				String[] temp = x.nextLine().split(","); 
				list.add(new VarholaM_lab11_Book(temp[0],
						temp[1],
						((temp[2].replace("-", "").trim().length()==10) ? new VarholaM_lab11_ISBN10(temp[2].replace("-", "").trim()) : new VarholaM_lab11_ISBN13(temp[2].replace("-", "").trim())),
						temp[3],
						temp[4],
						temp[5],
						Integer.parseInt(temp[6].trim())
						));
			}
			
			x.close();
			
			//DISPLAY CONTENTS OF ARRAY
			System.out.println(list.toString());
			
			//Display Title, ISBN, Checksum, ISBN Class, ValidISBN
			while (list.hasNext()){
				VarholaM_lab11_Book temp = (VarholaM_lab11_Book) list.getNext();
				
				System.out.printf("Book Title: %s \nISBN: %sChecksum: %d\nISBN Class: %s\nValid: %s\n\n",temp.getTitle(),temp.getIsbn().getIsbn(),temp.getIsbn().generateChecksum(),temp.getIsbn().getClass(),temp.getIsbn().isValid());
				writer.printf("ISBN: %s\tVALID: %s\n",temp.getIsbn().getIsbn(),temp.getIsbn().isValid());
				
			}
			x.close();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unknown Encoding!");
		}
		
		
		
		
	}

}
