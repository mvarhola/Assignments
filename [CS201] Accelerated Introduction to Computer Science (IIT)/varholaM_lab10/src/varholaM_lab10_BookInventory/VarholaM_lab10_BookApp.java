/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/02/2015
 * 	Program Name: BookApp (Lab 10 Activity)
 */


package varholaM_lab10_BookInventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VarholaM_lab10_BookApp {

	public static void main(String[] args) throws FileNotFoundException {
		VarholaM_lab10_ArrayList list = new VarholaM_lab10_ArrayList();
		File file = new File("bks13b.txt");
		Scanner x = new Scanner(file);
		
		while (x.hasNextLine()){
			String[] temp = x.nextLine().split(","); 
			list.add(new VarholaM_lab10_Book(temp[0],
					temp[1],
					((temp[2].replace("-", "").trim().length()==10) ? new VarholaM_lab10_ISBN10(temp[2].replace("-", "").trim()) : new VarholaM_lab10_ISBN13(temp[2].replace("-", "").trim())),
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
			VarholaM_lab10_Book temp = (VarholaM_lab10_Book) list.getNext();
			
			System.out.printf("Book Title: %s \n ISBN: %s\n Checksum: %d\n ISBN Class: %s\n Valid: %s\n\n",temp.getTitle(),temp.getIsbn().getIsbn(),temp.getIsbn().generateChecksum(),temp.getIsbn().getClass(),temp.getIsbn().isValid());
			
			
		}
		
		
	}

}
