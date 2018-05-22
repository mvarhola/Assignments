/*	
 * 	Name: Markiyan Varhola
 *	Course: CS-201
 * 	Section: 1
 * 	Date: 04/12/2015
 * 	Program Name: BookMethods (Lab 1 Activity)
 */

package varholaM_lab11_Exercises;

public class VarholaM_lab11_BookMethods<T> extends VarholaM_lab11_GenericList<T> {

	public VarholaM_lab11_BookMethods(){
		super();
		
	}
	
	public VarholaM_lab11_BookMethods(int size){
		super(size);
		
	}
	
	public int sequentialSearch(VarholaM_lab11_Book book){
		int bookIndex = -1;
		
		for (int i = 0; i < getIndex(); i++){
			if (getElement(i).equals(book)){
				bookIndex=i;
			}
		}
		
		return bookIndex;
	}
	
	public int binarySearch(VarholaM_lab11_Book book){
		int bookIndex = -1;
		int hi = getIndex()-1;
		int lo = 0;

		while (lo<=hi){
			int mid = (lo+hi)/2;

//			System.out.printf("MID: %d\tLO: %d\tHI: %d\n",mid,lo,hi);
//			System.out.println("Comparison: "+((Book) getElement(mid)).compareTo(book));
			
			if (( (VarholaM_lab11_Book) getElement(mid)).compareTo(book)>0){
				 hi = mid - 1;
			}
            else if (( (VarholaM_lab11_Book) getElement(mid)).compareTo(book)<0){
            	lo = mid + 1;
            }
            else{
            	bookIndex = mid;
            	break;
            }

		}
		
		return bookIndex;
	}
	
	public void selectSort(){
		for (int i = 0; i < getIndex() - 1; i++)
        {
            int pos = i;
            for (int j = i + 1; j < getIndex(); j++)
                if (((VarholaM_lab11_Book)getElement(j)).compareTo( (VarholaM_lab11_Book) getElement(pos)) < 1) 
                	pos = j;
            T smallerISBN = getElement(pos);  
            setElement(getElement(i),pos);
            setElement(smallerISBN, i);
        }
	}
	
}