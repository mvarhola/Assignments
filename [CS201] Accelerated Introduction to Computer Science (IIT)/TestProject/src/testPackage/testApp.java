package testPackage;

import java.util.ArrayList;


public class testApp {

	public static void main(String[] args){
		
		Pair<String, Integer> testPair = new Pair("Test",2);
		System.out.println(testPair.getFirst());
		System.out.println(testPair.getSecond());
		System.out.println(testPair.toString());
	
		String[] names = {"test","test11","test23123"};
		
		Pair<String,Integer> testPair2 = twoReturns(names);
		System.out.println(testPair2.toString());
	}
	

	public static Pair<String,Integer> twoReturns(String [] names)  {
		String name="";
		int pos=0;
		
		for (int i = 0; i < names.length;i++){
			if (names[i].length()>name.length()){
				name = names[i];
				pos = i;
			}
		}
		
		Pair<String,Integer> x = new Pair<String, Integer>(name,pos);
		
		return x;
	}

	
}
