package phoneParser;

public class TEST {

	public static void main(String[] args) {
		String test = "+ (788) 108-2919";
		test = test.trim().toUpperCase();
			
		test = test.replace("A", "2");
		test = test.replace("B", "2");
		test = test.replace("C", "2");

		test = test.replace("D", "3");
		test = test.replace("E", "3");
		test = test.replace("F", "3");
		
		test = test.replace("G", "4");
		test = test.replace("H", "4");
		test = test.replace("I", "4");

		test = test.replace("J", "5");
		test = test.replace("K", "5");
		test = test.replace("L", "5");

		test = test.replace("M", "6");
		test = test.replace("N", "6");
		test = test.replace("O", "6");
		
		test = test.replace("P", "7");
		test = test.replace("Q", "7");
		test = test.replace("R", "7");
		test = test.replace("S", "7");
		
		test = test.replace("T", "8");
		test = test.replace("U", "8");
		test = test.replace("V", "8");
		
		test = test.replace("W", "9");
		test = test.replace("X", "9");
		test = test.replace("Y", "9");
		test = test.replace("Z", "9");
		
		System.out.println(test = test.replaceAll("[^0-9+]+", ""));
		
		System.out.println(test.matches("[+]1\\W*([0-9][0-9][0-9])\\W*([0-9][0-9]{2})\\W*([0-9]{4})(\\se?x?t?(\\d*))?"));
		
		test = ((test.charAt(0)=='+') ? "": (test.charAt(0)=='1') ? "+":"+1") + test;
		System.out.println(test);

	}

}
