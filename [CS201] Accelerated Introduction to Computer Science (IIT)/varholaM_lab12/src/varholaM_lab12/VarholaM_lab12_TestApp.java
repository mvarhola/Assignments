/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/21/2015
 * 	Program Name: TestApp (Lab 12 Activity)
 */


package varholaM_lab12;


public class VarholaM_lab12_TestApp {

	public static void main(String[] args){

		
		VarholaM_lab12_SuperList<Integer> testArray = new VarholaM_lab12_SuperList<Integer>(40);
		
		for (int i = 0; i < testArray.getLength()-1;i++){
			testArray.add(i);
		}
		testArray.add(101);
		
		for (int i = 0; i < testArray.getLength();i++){
			System.out.printf("%d ",testArray.getElement(i));
		}
		System.out.println();
		
		testArray.sequentialSearch(-1);
		testArray.sequentialSearch(testArray.getElement(0));
		
		testArray.selectSort();
		
		System.out.println(testArray.binarySearch(testArray.getElement(testArray.getIndex()/2 -1)));
		testArray.binarySearch(-1);

		System.out.println("Testing recursive binary search");
		
		//Object Not Found
		System.out.println("Object not found: ");
		testArray.rbinarySearch(-1,0,testArray.getIndex()-1,1);
		
		System.out.println();
		//Object Found 1st
		System.out.println("Object Found First: ");
		testArray.rbinarySearch(testArray.getElement((testArray.getIndex()/2)-1), 0, testArray.getIndex()-1, 1);

		System.out.println("Testing recursive sequential search");

		System.out.println("Object not found: ");
		testArray.rsequentialSearch(-1,0,1);
		
		System.out.println("Object Found First: ");
		testArray.rsequentialSearch(testArray.getElement(0), 0, 1);
		
	}

}
