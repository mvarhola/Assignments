package phoneParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class PhoneParseApp {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File("PracticeInput.txt");
		Scanner x = new Scanner(file);
		String temp = "";
		PrintWriter writer = new PrintWriter("testnums2.txt", "UTF-8");
		
		while (x.hasNext()){
			temp = x.nextLine();
			temp = temp.trim().toUpperCase();
			
			temp = temp.replace("A", "2");
			temp = temp.replace("B", "2");
			temp = temp.replace("C", "2");

			temp = temp.replace("D", "3");
			temp = temp.replace("E", "3");
			temp = temp.replace("F", "3");
			
			temp = temp.replace("G", "4");
			temp = temp.replace("H", "4");
			temp = temp.replace("I", "4");

			temp = temp.replace("J", "5");
			temp = temp.replace("K", "5");
			temp = temp.replace("L", "5");

			temp = temp.replace("M", "6");
			temp = temp.replace("N", "6");
			temp = temp.replace("O", "6");
			
			temp = temp.replace("P", "7");
			temp = temp.replace("Q", "7");
			temp = temp.replace("R", "7");
			temp = temp.replace("S", "7");
			
			temp = temp.replace("T", "8");
			temp = temp.replace("U", "8");
			temp = temp.replace("V", "8");
			
			temp = temp.replace("W", "9");
			temp = temp.replace("X", "9");
			temp = temp.replace("Y", "9");
			temp = temp.replace("Z", "9");
			
			temp = temp.replaceAll("[^0-9+]+", "");
			temp = ((temp.charAt(0)=='+') ? "": (temp.charAt(0)=='1'&&(temp.length()!=7 && temp.length()!=10)) ? "+":"+1") + temp;

			if (temp.matches("[+]?[1]?\\W*([0-9][0-9][0-9])\\W*([0-9][0-9]{2})\\W*([0-9]{4})(\\se?x?t?(\\d*))?") && (temp.length()==12)){
				writer.println(temp);
			}else{
				System.out.println(temp);
				writer.println("Fleshling follow-up needed");
			}
		}
		writer.close();
		x.close();
	}

}
