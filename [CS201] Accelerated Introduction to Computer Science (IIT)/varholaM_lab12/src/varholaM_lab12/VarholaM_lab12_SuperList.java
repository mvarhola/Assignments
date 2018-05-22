/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/21/2015
 * 	Program Name: SuperList (Lab 12 Activity)
 */

package varholaM_lab12;

public class VarholaM_lab12_SuperList<T extends Comparable<T>> extends VarholaM_lab12_GenericList<T> {

	public VarholaM_lab12_SuperList(){
		super();
		
	}
	
	public VarholaM_lab12_SuperList(int size){
		super(size);
		
	}
	
	public int sequentialSearch(T book){
		int bookIndex = -1;
		int comparisons = 0;
		for (int i = 0; i < getIndex(); i++){
			comparisons++;
			if (getElement(i).equals(book)){
				bookIndex=i;
				break;
			}
		}
		System.out.println("SequentialSearchComparisons: "+comparisons);
		return bookIndex;
	}
	

	int rsequentialSearch(T x, int min, int comparisons){
		if ( min == getIndex()-1 ){
			System.out.println("rSequentialSearchComparisons: "+comparisons);
			return -1;
		}
		if (x.equals(getElement(min))){
			System.out.println("rSequentialSearchComparisons: "+comparisons);
			return min;
		}
		else{
			return rsequentialSearch(x, min+1, comparisons+1);
		}
	}
	
	public int binarySearch(T book){
		int bookIndex = -1;
		int hi = getIndex()-1;
		int lo = 0;
		int counter = 0;
		
		while (lo<=hi){
			counter++;
			int mid = (lo+hi)/2;

			//System.out.printf("MID: %d\tLO: %d\tHI: %d\n",mid,lo,hi);
			//System.out.println("Comparison: "+(getElement(mid)).compareTo(book));
			
			if ((getElement(mid)).compareTo(book)>0){
				 hi = mid - 1;
			}
            else if ((getElement(mid)).compareTo(book)<0){
            	lo = mid + 1;
            }
            else{	
            	bookIndex = mid;
            	break;
            }

		}
		System.out.println("BinarySearchComparisons: "+counter);
		return bookIndex;
	}
	
	public int rbinarySearch(T x, int low, int high, int comparisons) {
		if (low > high) return -1; 
		int mid = (low + high)/2;
		if (getElement(mid).equals(x)){
			System.out.println("R_BinarySearchComparisons: "+comparisons);
			return mid;			
		}
		else if (getElement(mid).compareTo(x)<1){
			System.out.println("R_BinarySearchComparisons: "+comparisons);
			return rbinarySearch(x, mid+1, high, comparisons+1);
		}
		else {
			System.out.println("R_BinarySearchComparisons: "+comparisons);
			return rbinarySearch(x, low, mid-1,comparisons+1);
		}
	   }
	
	public void selectSort(){
		int comparisons = 0;
		for (int i = 0; i < getIndex() - 1; i++)
        {
            int pos = i;
            for (int j = i + 1; j < getIndex(); j++){
            	comparisons++;
                if ((getElement(j)).compareTo(getElement(pos)) < 1) 
                	pos = j;
            }
            T smallerISBN = getElement(pos);  
            setElement(getElement(i),pos);
            setElement(smallerISBN, i);
        }
		System.out.println("SelectSortComparisons: "+comparisons);
	}
	
}