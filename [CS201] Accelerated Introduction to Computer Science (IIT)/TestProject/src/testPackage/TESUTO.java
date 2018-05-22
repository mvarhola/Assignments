package testPackage;

public class TESUTO {

	public static int sumElements(int[] a, int low, int high, int currentsum ){
		if (low<=high){
			return sumElements(a,low+1,high, currentsum+=a[low]);
		}
		return currentsum;
	}
	
	public static void printReverseInt(int x){
		if (x<10){
			System.out.println(x);
		}
		else{
			System.out.println(number%10);
			
		}
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,7,8,9};
		System.out.println(sumElements(array,2,5,0));
		
	}

}
