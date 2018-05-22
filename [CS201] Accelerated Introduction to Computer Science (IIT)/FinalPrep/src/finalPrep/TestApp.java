package finalPrep;

public class TestApp {

	public static void reverseStuff(int[] x){
		for (int i = 0; i < x.length/2; i++){
			int temp = x[x.length-i-1];
			x[x.length-i-1] = x[i];
			x[i]=temp;
		}
		for(int i:x){
			System.out.printf("%d ",i);
		}
	}
	
	public static void reverse(int[] x, int i, int j){
	    if(i<j){//Swap
	       int tmp = x[i];
	       x[i] = x[j];
	       x[j] = tmp;
	       reverse(x, ++i, --j);//Recursive
	    }   
	}
	
	public static int lowest(int[] x, int i, int min){
	    if (i<x.length-1){
	    	System.out.printf("%d,%d\n",min,x[i+1]);
	    	if(x[i+1]<min){//Swap
	    		min=x[i+1];
	    		return lowest(x,++i,min);
	    	}
	    }
	    System.out.println(min);
   		return min;

	}
	
	
	public static void main(String[] args) {
		int[] x = {1,2,3,4,5,6,7};
		reverseStuff(x);
		
		System.out.println();
		int[] y = {1,2,3,4,5,6,7};
		reverse(y,0,y.length-1);
		for(int i:y){
			System.out.printf("%d ",i);
		}
		System.out.println();

		lowest(y,0,y[0]);

	}

}
