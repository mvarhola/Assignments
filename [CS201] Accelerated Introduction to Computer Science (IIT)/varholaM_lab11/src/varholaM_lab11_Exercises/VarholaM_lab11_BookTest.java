/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/12/2015
 * 	Program Name: BookTESTER App (Lab 11 Activity)
 */


package varholaM_lab11_Exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VarholaM_lab11_BookTest {

	public static void main(String[] args) throws FileNotFoundException {

		VarholaM_lab11_GenericList<VarholaM_lab11_Book> list = new VarholaM_lab11_GenericList<VarholaM_lab11_Book>();
		File file = new File("bks13b.txt");
		
		Scanner x;
		
		x = new Scanner(file);
		
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
		
//		//Display Title, ISBN, Checksum, ISBN Class, ValidISBN
//		while (list.hasNext()){
//			Book temp = (Book) list.getNext();
//			
//			System.out.printf("Book Title: %s \nISBN: %sChecksum: %d\nISBN Class: %s\nValid: %s\n\n",temp.getTitle(),temp.getIsbn().getIsbn(),temp.getIsbn().generateChecksum(),temp.getIsbn().getClass(),temp.getIsbn().isValid());
//			
//		}
		x.close();
		
		//BEGIN TESTS
		
		//Equals Test Case
		
		System.out.println(list.getElement(0).equals(list.getElement(0))); //should return true
		System.out.println(list.getElement(0).equals(list.getElement(1))); //should return false

		System.out.println(list.getElement(1).compareTo(list.getElement(0)));	//[1]Author (Steve...) > [0]Author (Robert...), should return -1
		System.out.println(list.getElement(0).compareTo(list.getElement(1))); 	//[0]Author (Robert...) < [1]Author (Steve), should return 1
		System.out.println(list.getElement(0).compareTo(list.getElement(0)));	//equal elements, should return 0
		
		VarholaM_lab11_BookMethods<VarholaM_lab11_Book> test = new VarholaM_lab11_BookMethods<VarholaM_lab11_Book>();
		System.out.println(test.getLength());
		
		test = new VarholaM_lab11_BookMethods<VarholaM_lab11_Book>(42);
		System.out.println(test.getLength());
		
		System.out.println(test.getIndex()); //No Elements, should be 0
		test.add(list.getElement(0));
		System.out.println(test.getIndex()); //index = 1
		test.add(list.getElement(1));
		System.out.println(test.getIndex()); //index = 2
		test.add(list.getElement(2));
		System.out.println(test.getIndex()); //index = 3
		test.add(list.getElement(3));
		System.out.println(test.getIndex()); //index = 4

		System.out.println(test.toString());
		System.out.println(test.sequentialSearch(list.getElement(2)));
		System.out.println(test.sequentialSearch(list.getElement(5)));
		
		System.out.println("Begin Select Sort");
		test.selectSort();
		System.out.println(test.toString());

		System.out.println("Begin Binary Search");

		System.out.println(test.binarySearch(list.getElement(3))); //Search for Don't Make Me Think by Steve Krug
		System.out.println(test.binarySearch(list.getElement(5))); //Search for Fifty Shades Of Grey (INCORRECT ISBN)
		
	}

}
